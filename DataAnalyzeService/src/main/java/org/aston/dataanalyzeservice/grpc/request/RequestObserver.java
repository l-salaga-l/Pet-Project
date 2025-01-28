package org.aston.dataanalyzeservice.grpc.request;

import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aston.dataanalyzeservice.service.ReportService;
import org.aston.dataanalyzeservice.util.PulseAnalyzer;
import org.aston.proto.Input;
import org.aston.proto.Output;

@Slf4j
@RequiredArgsConstructor
public class RequestObserver implements StreamObserver<Input> {
    private final ReportService reportService;
    private final StreamObserver<Output> responseObserver;
    private String result;

    @Override
    public void onNext(Input value) {
        log.info("Received value: {}", value.getPulse());
        result = PulseAnalyzer.getPulseCategory(value.getPulse());
    }

    @Override
    public void onError(Throwable t) {
        log.error("Error: {}", t.getMessage());
    }

    @Override
    public void onCompleted() {
        Output response = Output.newBuilder().setResult(result).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
        reportService.createReport("Измерение пульса", "Результат:" + result);
        log.info("Consumer completed, result: {}", result);
    }
}
