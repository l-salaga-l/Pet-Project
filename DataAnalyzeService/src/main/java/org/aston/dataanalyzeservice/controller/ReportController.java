package org.aston.dataanalyzeservice.controller;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.aston.dataanalyzeservice.dto.response.ReportCreatedResponseDto;
import org.aston.dataanalyzeservice.dto.response.ReportResponseDto;
import org.aston.dataanalyzeservice.service.ReportService;
import org.hibernate.validator.constraints.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/report")
public class ReportController {
    private final ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping(value = "/{reportId}")
    public ResponseEntity<ReportResponseDto> getReport(@PathVariable("reportId") @UUID String reportId) {
        return new ResponseEntity<>(reportService.getReport(reportId), HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<ReportCreatedResponseDto> createReport(
            @RequestParam("author") String author,
            @RequestParam("title") String title) {
        return new ResponseEntity<>(reportService.createReport(author, title), HttpStatus.CREATED);
    }
}
