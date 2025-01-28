package org.aston.dataanalyzeservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.aston.dataanalyzeservice.dto.response.ReportCreatedResponseDto;
import org.aston.dataanalyzeservice.dto.response.ReportResponseDto;
import org.aston.dataanalyzeservice.exception.ReportNotFoundException;
import org.aston.dataanalyzeservice.kafka.producer.KafkaProducer;
import org.aston.dataanalyzeservice.mapper.ReportResponseMapper;
import org.aston.dataanalyzeservice.model.Report;
import org.aston.dataanalyzeservice.repository.ReportRepository;
import org.aston.dataanalyzeservice.service.ReportService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {
    private final KafkaProducer kafkaProducer;
    private final ReportRepository reportRepository;
    private final ReportResponseMapper reportResponseMapper;

    @Override
    public ReportCreatedResponseDto createReport(String title, String content) {
        Report report = Report.builder()
                .title(title)
                .content(content)
                .createdDate(LocalDate.now())
                .build();

        report.setUpdatedDate(report.getCreatedDate());

        report = reportRepository.save(report);

        kafkaProducer.send(reportResponseMapper.toEvent(report));

        return reportResponseMapper.toReportCreatedDto(report);
    }

    @Override
    public ReportResponseDto getReport(String id) {
        Report report = reportRepository.findById(UUID.fromString(id))
                .orElseThrow(ReportNotFoundException::new);

        return reportResponseMapper.toReportResponseDto(report);
    }
}
