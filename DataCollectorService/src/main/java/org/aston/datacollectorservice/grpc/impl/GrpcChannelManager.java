package org.aston.datacollectorservice.grpc.impl;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import jakarta.annotation.PreDestroy;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static java.util.concurrent.TimeUnit.SECONDS;

@Slf4j
@Getter
@Component
public class GrpcChannelManager {
    private final ManagedChannel channel;

    public GrpcChannelManager() {
        this.channel = ManagedChannelBuilder.forAddress("localhost", 8000)
                .usePlaintext()
                .build();
    }

    @PreDestroy
    public void shutdown() {
        try {
            channel.shutdown().awaitTermination(5, SECONDS);
        } catch (InterruptedException e) {
            log.warn("Channel shutdown interrupted");
            Thread.currentThread().interrupt();
        }
    }
}
