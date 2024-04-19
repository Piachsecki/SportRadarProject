package org.example.controller.file;

import org.springframework.http.HttpStatus;

public record FileUploadResponse(
        String fileName,
        HttpStatus httpStatus
) {
}
