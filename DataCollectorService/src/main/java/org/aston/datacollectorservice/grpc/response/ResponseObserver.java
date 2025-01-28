package org.aston.datacollectorservice.grpc.response;

import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import org.aston.proto.Output;

@Slf4j
public class ResponseObserver implements StreamObserver<Output> {
    private final Object lock = new Object();
    private boolean completed = false;
    private Output response = Output.newBuilder().build();

    @Override
    public void onNext(Output value) {
        response = Output.newBuilder(value).build();
        log.info("Response: {}", value.getResult());
    }

    @Override
    public void onError(Throwable t) {
        log.error("Error occurred: {}", t.getMessage());
        synchronized (lock) {
            completed = true;
            lock.notifyAll();
        }
    }

    @Override
    public void onCompleted() {
        log.info("Response completed");
        synchronized (lock) {
            completed = true;
            lock.notifyAll();
        }
    }

    public String getResult() {
        synchronized (lock) {
            return response.getResult();
        }
    }

    public void awaitCompletion() {
        synchronized (lock) {
            while (!completed) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    log.error(e.getMessage());
                }
            }
        }
    }
}
