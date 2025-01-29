package org.aston.dataanalyzeservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.WebRequest;

@Data
@AllArgsConstructor
public class ErrorResponse {
    private int status;
    private String message;
    private String path;

    public ErrorResponse(String message, HttpStatus httpStatus, WebRequest request) {
        this.message = message;
        this.status = httpStatus.value();
        this.path = request.getContextPath();
    }
}
