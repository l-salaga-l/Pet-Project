package org.aston.datacollectorservice.grpc.impl;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import org.aston.datacollectorservice.grpc.GrpcCollectorService;
import org.aston.datacollectorservice.grpc.response.ResponseObserver;
import org.aston.proto.Input;
import org.springframework.stereotype.Service;

import org.aston.proto.AnalyzeServiceGrpc;


import java.util.Random;

@Slf4j
@Service
public class GrpcCollectorServiceImpl implements GrpcCollectorService {
    private final Random random = new Random();

    public String send() {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8000).usePlaintext().build();

        AnalyzeServiceGrpc.AnalyzeServiceStub analyzeServiceStub = AnalyzeServiceGrpc.newStub(channel);

        ResponseObserver responseObserver = new ResponseObserver();

        StreamObserver<Input> requestObserver = analyzeServiceStub.getResult(responseObserver);

        for (int i = 0; i < 10; i++) {
            Input input = Input.newBuilder()
                    .setPulse(random.nextInt(61) + 50)
                    .build();

            requestObserver.onNext(input);
        }

        requestObserver.onCompleted();

        responseObserver.awaitCompletion();

        channel.shutdownNow();

        return responseObserver.getResult();
    }
}
