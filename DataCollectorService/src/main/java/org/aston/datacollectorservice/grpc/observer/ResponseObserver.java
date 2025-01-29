package org.aston.datacollectorservice.grpc.observer;

import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import org.aston.proto.Output;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ResponseObserver implements StreamObserver<Output> {
    private final CountDownLatch completionLatch = new CountDownLatch(1);
    private volatile PulseData finalMeasurement;

    @Override
    public void onNext(Output output) {
        log.info("Received pulse measurement: {}", output.getAvgPulse());
        finalMeasurement = new PulseData(output.getAvgPulse(), output.getResult());
    }

    @Override
    public void onError(Throwable t) {
        log.error("gRPC error: {}", t.getMessage());
        completionLatch.countDown();
    }

    @Override
    public void onCompleted() {
        log.info("gRPC call completed successfully");
        completionLatch.countDown();
    }

    public boolean awaitCompletion(long timeout, TimeUnit unit) throws InterruptedException {
        return completionLatch.await(timeout, unit);
    }

    public PulseData getFinalMeasurement() {
        if (finalMeasurement == null) {
            throw new IllegalStateException("No measurements received");
        }
        return finalMeasurement;
    }

    public record PulseData(int pulseValue, String response) {}
}
