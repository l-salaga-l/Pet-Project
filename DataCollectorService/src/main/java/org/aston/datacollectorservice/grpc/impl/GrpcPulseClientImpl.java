package org.aston.datacollectorservice.grpc.impl;

import io.grpc.ManagedChannel;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aston.datacollectorservice.dto.response.PulseAnalyzeResponseDto;
import org.aston.datacollectorservice.grpc.GrpcPulseClient;
import org.aston.datacollectorservice.grpc.observer.ResponseObserver;
import org.aston.datacollectorservice.mapper.PulseMapper;
import org.aston.proto.Input;
import org.springframework.stereotype.Service;
import org.aston.proto.AnalyzeServiceGrpc;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

import static java.util.concurrent.TimeUnit.SECONDS;

@Slf4j
@Service
@RequiredArgsConstructor
public class GrpcPulseClientImpl implements GrpcPulseClient {
    private final PulseMapper pulseMapper;
    private final GrpcChannelManager channelManager;

    @Override
    public PulseAnalyzeResponseDto fetchPulseMeasurement() {
        ManagedChannel channel = channelManager.getChannel();
        AnalyzeServiceGrpc.AnalyzeServiceStub stub = AnalyzeServiceGrpc.newStub(channel);
        ResponseObserver responseObserver = new ResponseObserver();

        StreamObserver<Input> requestObserver = stub.getResult(responseObserver);
        sendPulseMeasurements(requestObserver);

        return waitForResponse(responseObserver);
    }

    private void sendPulseMeasurements(StreamObserver<Input> requestObserver) {
        IntStream.range(0, 10)
                .mapToObj(i -> Input.newBuilder().setPulse(generatePulseValue()).build())
                .forEach(requestObserver::onNext);

        requestObserver.onCompleted();
    }

    private int generatePulseValue() {
        return ThreadLocalRandom.current().nextInt(50, 111);
    }

    private PulseAnalyzeResponseDto waitForResponse(ResponseObserver responseObserver) {
        try {
            if (!responseObserver.awaitCompletion(20, SECONDS)) {
                throw new RuntimeException("Timeout waiting for gRPC response");
            }
            return pulseMapper.toDto(responseObserver.getFinalMeasurement());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Measurement interrupted", e);
        }
    }
}
