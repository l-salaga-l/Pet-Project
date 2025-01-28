package org.aston.dataanalyzeservice.mapper;

import org.aston.dataanalyzeservice.dto.event.ReportCreatedEvent;
import org.aston.dataanalyzeservice.dto.response.ReportCreatedResponseDto;
import org.aston.dataanalyzeservice.dto.response.ReportResponseDto;
import org.aston.dataanalyzeservice.model.Report;
import org.springframework.stereotype.Component;

@Component
public class ReportResponseMapperImpl implements ReportResponseMapper{
    @Override
    public ReportCreatedResponseDto toReportCreatedDto(Report report) {
        return ReportCreatedResponseDto.builder()
                .reportId(report.getId().toString())
                .title(report.getTitle())
                .author(report.getAuthor())
                .createdDate(report.getCreatedDate())
                .build();
    }

    @Override
    public ReportCreatedEvent toEvent(Report report) {
        return ReportCreatedEvent.builder()
                .reportId(report.getId().toString())
                .reportName(report.getTitle())
                .createdDate(report.getCreatedDate())
                .build();
    }

    @Override
    public ReportResponseDto toReportResponseDto(Report report) {
        return ReportResponseDto.builder()
                .title(report.getTitle())
                .content(report.getContent())
                .updatedDate(report.getUpdatedDate())
                .build();
    }
}
