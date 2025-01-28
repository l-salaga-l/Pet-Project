package org.aston.dataanalyzeservice.exception;

import java.util.UUID;

public class ReportNotFoundException extends RuntimeException {
    private static final String REPORT_NOT_FOUND = "Report not found";
    private static final String REPORT_NOT_FOUND_WITH_ID = "Report not found with id: %s";

    public ReportNotFoundException() {
        super(REPORT_NOT_FOUND);
    }

    public ReportNotFoundException(String message) {
        super(message);
    }

    public ReportNotFoundException(UUID id) {
        super(String.format(REPORT_NOT_FOUND_WITH_ID, id));
    }
}
