package org.aston.datacollectorservice.service;

import org.aston.datacollectorservice.dto.response.PulseAnalyzeResponseDto;

public interface CollectorService {
    PulseAnalyzeResponseDto collectPulseData();
}
