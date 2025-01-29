package org.aston.dataanalyzeservice.grpc;

import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import org.aston.dataanalyzeservice.grpc.request.RequestObserver;
import org.aston.dataanalyzeservice.service.PulseAnalysisService;
import org.aston.dataanalyzeservice.service.ReportService;
import org.aston.proto.AnalyzeServiceGrpc;
import org.aston.proto.Input;
import org.aston.proto.Output;
import org.lognet.springboot.grpc.GRpcService;

@GRpcService
@RequiredArgsConstructor
public class GrpcAnalyzeService extends AnalyzeServiceGrpc.AnalyzeServiceImplBase {
    private final ReportService reportService;
    private final PulseAnalysisService pulseAnalysisService;

    @Override
    public StreamObserver<Input> getResult(StreamObserver<Output> responseObserver) {
        return new RequestObserver(reportService, pulseAnalysisService, responseObserver);
    }
}
