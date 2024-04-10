package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import lombok.Getter;
import org.example.domain.Event;
import org.example.exception.TooManyExpectedMatchesException;
import org.example.json.ZonedDateTimeDeserializer;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
public class MatchFinder {
    private List<Event> events;


    public MatchFinder(String filePath) {
        Gson gson = new GsonBuilder().registerTypeAdapter(ZonedDateTime.class, new ZonedDateTimeDeserializer()).create();
        this.events = new ArrayList<>();

        try {
            this.events = gson.fromJson(new FileReader(filePath), new TypeToken<List<Event>>() {
            }.getType());

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public List<Event> highestProbableValues(List<Event> events, int numberOfExpectedMatches) {
        checkIfNumberOfExpectedMatchesIsCorrect(events.size(), numberOfExpectedMatches);
        List<Event> result = new ArrayList<>(numberOfExpectedMatches);

        Map<String, BigDecimal> highestProbabilityForId = events.stream()
                .collect(
                        Collectors
                                .toMap(
                                        Event::getSport_event_id,
                                        event -> findMax(event.getProbability_draw(),
                                                event.getProbability_home_team_winner(),
                                                event.getProbability_away_team_winner())));
        for (int i = 0; i < numberOfExpectedMatches; i++) {
            String eventId = findExactEventId(highestProbabilityForId);
            result.add(this.events.stream().filter(event -> eventId.equals(event.getSport_event_id())).findFirst().orElseThrow());
        }

        return result;
    }

    private String findExactEventId(Map<String, BigDecimal> highestProbabilityForId) {
        BigDecimal max = BigDecimal.ZERO;
        String keyToReturn = null;
        for (Map.Entry<String, BigDecimal> entry : highestProbabilityForId.entrySet()) {
            if(max.compareTo(entry.getValue()) < 0){
                max = entry.getValue();
                keyToReturn = entry.getKey();
            }
        }
        highestProbabilityForId.remove(keyToReturn, max);
        return keyToReturn;

    }

    private void checkIfNumberOfExpectedMatchesIsCorrect(int sizeOfAllEvents, int numberOfExpectedMatches) {
        if (numberOfExpectedMatches > sizeOfAllEvents) {
            throw new TooManyExpectedMatchesException(
                    String.format(
                            "You wanted to get more matches[%s] than there are in the list[%s]!",
                            numberOfExpectedMatches,
                            sizeOfAllEvents
                    ));
        }
    }


    private BigDecimal findMax(BigDecimal... values) {
        return Arrays.stream(values)
                .max(BigDecimal::compareTo)
                .orElse(BigDecimal.ZERO);
    }


}
