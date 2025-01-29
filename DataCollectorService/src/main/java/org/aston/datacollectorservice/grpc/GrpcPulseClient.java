package org.aston.datacollectorservice.grpc;

import org.aston.datacollectorservice.dto.response.PulseAnalyzeResponseDto;

public interface GrpcPulseClient {
    PulseAnalyzeResponseDto fetchPulseMeasurement();
}
