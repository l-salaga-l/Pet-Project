package org.aston.dataanalyzeservice.exception;

public class ReportNotFoundException extends RuntimeException {
    private static final String REPORT_NOT_FOUND = "Report not found";

    public ReportNotFoundException() {
        super(REPORT_NOT_FOUND);
    }
}
