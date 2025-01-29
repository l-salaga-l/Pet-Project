package org.aston.dataanalyzeservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.aston.dataanalyzeservice.dto.response.ReportCreatedResponseDto;
import org.aston.dataanalyzeservice.dto.response.ReportResponseDto;
import org.aston.dataanalyzeservice.exception.ReportNotFoundException;
import org.aston.dataanalyzeservice.kafka.producer.KafkaProducer;
import org.aston.dataanalyzeservice.mapper.ReportMapper;
import org.aston.dataanalyzeservice.model.Report;
import org.aston.dataanalyzeservice.repository.ReportRepository;
import org.aston.dataanalyzeservice.service.ReportService;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {
    private final KafkaProducer kafkaProducer;
    private final ReportRepository reportRepository;
    private final ReportMapper reportMapper;

    @Override
    public ReportCreatedResponseDto createReport(String title, String content) {
        Report report = Report.builder()
                .title(title)
                .content(content)
                .build();

        report = reportRepository.save(report);

        kafkaProducer.send(reportMapper.toEvent(report));

        return reportMapper.toReportCreatedDto(report);
    }

    @Override
    public ReportResponseDto getReport(String id) {
        Report report = reportRepository.findById(UUID.fromString(id))
                .orElseThrow(ReportNotFoundException::new);

        return reportMapper.toReportResponseDto(report);
    }
}
