package org.aston.datacollectorservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.aston.datacollectorservice.dto.response.PulseAnalyzeResponseDto;
import org.aston.datacollectorservice.grpc.GrpcPulseClient;
import org.aston.datacollectorservice.service.CollectorService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CollectorServiceImpl implements CollectorService {
    private final GrpcPulseClient grpcPulseClient;

    @Override
    public PulseAnalyzeResponseDto collectPulseData() {
        return grpcPulseClient.fetchPulseMeasurement();
    }
}
