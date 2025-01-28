package org.aston.dataanalyzeservice.dto.event;

import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReportCreatedEvent {
    private String reportId;
    private String reportName;
    private LocalDate createdDate;
}
