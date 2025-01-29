package org.aston.dataanalyzeservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReportCreatedResponseDto {
    private String reportId;
    private String author;
    private String title;
    private LocalDateTime creationDate;
}
