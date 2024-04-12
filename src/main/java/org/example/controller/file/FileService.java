package org.example.controller.file;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.example.domain.Event;
import org.example.json.ZonedDateTimeDeserializer;
import org.example.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileService {

    private final StorageService storageService;
    private final EventService eventService;

    @Autowired
    public FileService(StorageService storageService, EventService eventService) {
        this.storageService = storageService;
        this.eventService = eventService;
    }

    public FileUploadResponse upload(FileUploadRequest fileUploadRequest) {
        MultipartFile file = fileUploadRequest.file();
        try {
            // Save the file using the storage service
            String filePath = storageService.store(file);
            Gson gson = new GsonBuilder().registerTypeAdapter(ZonedDateTime.class, new ZonedDateTimeDeserializer()).create();
            List<Event> events = new ArrayList<>();
            try {

                events = gson.fromJson(new FileReader(filePath), new TypeToken<List<Event>>() {
                }.getType());
                events.forEach(event -> {
                    try {
                        eventService.addEvent(event);
                    } catch (Exception e) {
                        // Log or handle the exception
                        e.printStackTrace();
                    }
                });

            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            return null;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
