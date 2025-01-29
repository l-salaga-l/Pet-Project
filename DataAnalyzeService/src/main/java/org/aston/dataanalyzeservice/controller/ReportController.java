package org.aston.dataanalyzeservice.controller;

import lombok.RequiredArgsConstructor;
import org.aston.dataanalyzeservice.dto.response.ReportCreatedResponseDto;
import org.aston.dataanalyzeservice.dto.response.ReportResponseDto;
import org.aston.dataanalyzeservice.service.ReportService;
import org.hibernate.validator.constraints.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/reports")
@RequiredArgsConstructor
public class ReportController {
    private final ReportService reportService;

    @GetMapping("/{id}")
    public ResponseEntity<ReportResponseDto> getReport(@PathVariable("id") @UUID String reportId) {
        return new ResponseEntity<>(reportService.getReport(reportId), HttpStatus.OK);
    }

    @PostMapping(value = "/")
    public ResponseEntity<ReportCreatedResponseDto> createReport(
            @RequestParam("author") String author,
            @RequestParam("title") String title) {
        return new ResponseEntity<>(reportService.createReport(author, title), HttpStatus.CREATED);
    }
}
