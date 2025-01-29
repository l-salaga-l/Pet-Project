package org.aston.dataanalyzeservice.mapper;

import org.aston.dataanalyzeservice.dto.event.ReportCreatedEvent;
import org.aston.dataanalyzeservice.dto.response.ReportCreatedResponseDto;
import org.aston.dataanalyzeservice.dto.response.ReportResponseDto;
import org.aston.dataanalyzeservice.model.Report;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReportMapper {

    @Mapping(target = "reportId", source = "id")
    ReportCreatedResponseDto toReportCreatedDto(Report report);

    @Mapping(target = "reportId", source = "id")
    @Mapping(target = "reportName", source = "title")
    ReportCreatedEvent toEvent(Report report);

    ReportResponseDto toReportResponseDto(Report report);
}
