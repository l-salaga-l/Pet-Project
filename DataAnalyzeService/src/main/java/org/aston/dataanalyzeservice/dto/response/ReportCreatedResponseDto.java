package org.aston.dataanalyzeservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReportCreatedResponseDto {
    private String reportId;
    private String author;
    private String title;
    private LocalDate createdDate;
}
