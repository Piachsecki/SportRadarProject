package org.example.controller.file;

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
    public String store(MultipartFile file) throws IOException {
        // Generate unique filename
//        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//        String fileExtension = getFileExtension(StringUtils.cleanPath(file.getOriginalFilename()));
//        String uniqueFileName = UUID.randomUUID().toString() + "." + fileExtension;

        // Resolve upload directory path
        Path uploadPath = Paths.get("src", "main", "resources", "static").toAbsolutePath().normalize();

        // Create the upload directory if it doesn't exist
        uploadPath.toFile().mkdirs();

        // Copy file to the upload directory
        Path targetLocation = uploadPath.resolve(file.getOriginalFilename());
        Files.copy(file.getInputStream(), targetLocation);

        return String.valueOf(targetLocation);
    }

//    private String getFileExtension(String fileName) {
//        int dotIndex = fileName.lastIndexOf('.');
//        return (dotIndex == -1) ? "" : fileName.substring(dotIndex + 1);
//    }
}
