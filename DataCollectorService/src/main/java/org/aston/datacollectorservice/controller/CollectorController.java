package org.aston.datacollectorservice.controller;

import lombok.RequiredArgsConstructor;
import org.aston.datacollectorservice.dto.response.PulseAnalyzeResponseDto;
import org.aston.datacollectorservice.service.CollectorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( "/api")
@RequiredArgsConstructor
public class CollectorController {
    private final CollectorService collectorService;

    @GetMapping("/pulse")
    public ResponseEntity<PulseAnalyzeResponseDto> getPulseMeasurement() {
        return ResponseEntity.ok(collectorService.collectPulseData());
    }
}
