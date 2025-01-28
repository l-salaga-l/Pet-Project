package org.aston.dataanalyzeservice.mapper;

import org.aston.dataanalyzeservice.dto.event.ReportCreatedEvent;
import org.aston.dataanalyzeservice.dto.response.ReportCreatedResponseDto;
import org.aston.dataanalyzeservice.dto.response.ReportResponseDto;
import org.aston.dataanalyzeservice.model.Report;

public interface ReportResponseMapper {

    ReportCreatedResponseDto toReportCreatedDto(Report report);

    ReportCreatedEvent toEvent(Report report);

    ReportResponseDto toReportResponseDto(Report report);
}
