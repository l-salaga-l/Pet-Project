package org.aston.dataanalyzeservice.grpc.request;

import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aston.dataanalyzeservice.service.PulseAnalysisService;
import org.aston.dataanalyzeservice.service.ReportService;
import org.aston.proto.Input;
import org.aston.proto.Output;

@Slf4j
@RequiredArgsConstructor
public class RequestObserver implements StreamObserver<Input> {
    private final ReportService reportService;
    private final PulseAnalysisService pulseAnalysisService;
    private final StreamObserver<Output> responseObserver;

    private int pulseSum = 0;
    private int pulseCount = 0;
    private String pulseCategory;

    @Override
    public void onNext(Input input) {
        validateInput(input);
        log.info("Received pulse measurement: {}", input.getPulse());

        pulseSum += input.getPulse();
        pulseCount++;
        pulseCategory = pulseAnalysisService.determinePulseCategory(input.getPulse());
    }

    @Override
    public void onError(Throwable throwable) {
        log.error("Error processing stream: {}", throwable.getMessage());
        responseObserver.onError(throwable);
    }

    @Override
    public void onCompleted() {
        try {
            int avgPulse = calculateAveragePulse();
            sendResponse(avgPulse);
            createReport(avgPulse);
            log.info("Processed {} measurements. Average pulse: {}", pulseCount, avgPulse);
        } catch (Exception e) {
            log.error("Error completing request: {}", e.getMessage());
            responseObserver.onError(e);
        } finally {
            responseObserver.onCompleted();
        }
    }

    private void validateInput(Input input) {
        if (input.getPulse() <= 0) {
            throw new IllegalArgumentException("Пульс должен быть положительным числом");
        }
    }

    private int calculateAveragePulse() {
        if (pulseCount == 0) {
            throw new IllegalStateException("No pulse measurements received");
        }
        return pulseSum / pulseCount;
    }

    private void sendResponse(int avgPulse) {
        Output response = Output.newBuilder()
                .setAvgPulse(avgPulse)
                .setResult(pulseCategory)
                .build();
        responseObserver.onNext(response);
    }

    private void createReport(int avgPulse) {
        String reportContent = String.format(
                "Средний пульс: %d, Результат анализа: %s",
                avgPulse,
                pulseCategory
        );
        reportService.createReport("Анализ пульса", reportContent);
    }
}
