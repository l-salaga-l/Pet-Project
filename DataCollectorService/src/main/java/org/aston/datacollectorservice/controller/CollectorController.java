package org.aston.datacollectorservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aston.datacollectorservice.service.CollectorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
public class CollectorController {
    private final CollectorService collectorService;

    @GetMapping(value = "/pulse")
    public ResponseEntity<String> pulse() {
        return new ResponseEntity<>(collectorService.send(), HttpStatus.OK);
    }
}
