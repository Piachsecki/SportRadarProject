package org.example.controller.file;

import org.springframework.web.multipart.MultipartFile;

public record FileUploadRequest(
        MultipartFile file,
        String name
) {
}
