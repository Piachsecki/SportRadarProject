package org.example.controller.file;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class StorageService {

    @Value("${upload.dir}")
    private String uploadDir;

    public String store(MultipartFile file) throws IOException {
        // Generate unique filename
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String fileExtension = getFileExtension(fileName);
        String uniqueFileName = UUID.randomUUID().toString() + "." + fileExtension;

        // Resolve upload directory path
        Path uploadPath = Paths.get("src", "main", "resources", "static").toAbsolutePath().normalize();

        // Create the upload directory if it doesn't exist
        uploadPath.toFile().mkdirs();

        // Copy file to the upload directory
        Path targetLocation = uploadPath.resolve(uniqueFileName);
        Files.copy(file.getInputStream(), targetLocation);

        return String.valueOf(targetLocation);
    }

    private String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        return (dotIndex == -1) ? "" : fileName.substring(dotIndex + 1);
    }
}
