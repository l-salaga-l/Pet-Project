package org.aston.dataanalyzeservice.service;

import org.aston.dataanalyzeservice.dto.response.ReportCreatedResponseDto;
import org.aston.dataanalyzeservice.dto.response.ReportResponseDto;

public interface ReportService {
    ReportCreatedResponseDto createReport(String title, String content);

    ReportResponseDto getReport(String id);
}
