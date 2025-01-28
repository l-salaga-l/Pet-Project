package org.aston.datacollectorservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.aston.datacollectorservice.grpc.GrpcCollectorService;
import org.aston.datacollectorservice.service.CollectorService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CollectorServiceImpl implements CollectorService {
    private final GrpcCollectorService grpcCollectorService;

    @Override
    public String send() {
        return grpcCollectorService.send();
    }
}
