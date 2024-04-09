package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import lombok.Getter;
import org.example.domain.Event;
import org.example.json.ZonedDateTimeDeserializer;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class MatchFinder {
    private List<Event> events;


    public MatchFinder(String filePath) {
        Gson gson = new GsonBuilder().registerTypeAdapter(ZonedDateTime.class, new ZonedDateTimeDeserializer()).create();
        this.events = new ArrayList<>();

        try {
            this.events = gson.fromJson(new FileReader(filePath), new TypeToken<List<Event>>() {
            }.getType());


            for (Event event : events) {
                System.out.println(event);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }


    }


}
