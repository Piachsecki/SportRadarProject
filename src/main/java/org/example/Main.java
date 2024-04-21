package org.example;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.example.domain.Competitor;
import org.example.domain.Event;
import org.example.domain.Venue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
//        MatchFinder matchFinder = new MatchFinder();
//        List<Event> events = matchFinder.highestProbableValues(matchFinder.getEvents(), 20);
//        events.forEach(PrintingUtil::printTheResults);

        String apiUrl = "https://api.sportradar.com/soccer/trial/v4/en/seasons/sr:season:93741/probabilities.json";
        String apiKey = "MkPWxFhv076SQ2gpAx6U1OADJsJjLPg8FA3dp4Ah";
        try {
            // Create URL object with parameters
            URL url = new URL(apiUrl + "?api_key=" + apiKey);

            // Create HttpURLConnection object
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // Set request method to GET
            conn.setRequestMethod("GET");

            // Read response
            Scanner scanner = new Scanner(conn.getInputStream());
            StringBuilder response = new StringBuilder();
            while (scanner.hasNextLine()) {
                response.append(scanner.nextLine());
            }
            scanner.close();
//            Gson gson = new GsonBuilder().registerTypeAdapter(ZonedDateTime.class, new ZonedDateTimeDeserializer()).create();

            JsonObject jsonObject = JsonParser.parseString(response.toString()).getAsJsonObject();
            JsonArray sportEventProbabilities = jsonObject.getAsJsonArray("sport_event_probabilities");
            JsonObject sportEvent = sportEventProbabilities.get(0).getAsJsonObject().getAsJsonObject("sport_event");

            String eventId = sportEvent.get("id").getAsString();
            ZonedDateTime startTime = ZonedDateTime.parse(sportEvent.get("start_time").getAsString());
            String sportName = sportEvent.getAsJsonObject("sport_event_context").getAsJsonObject("sport").get("name").getAsString();
            String competitionName = sportEvent.getAsJsonObject("sport_event_context").getAsJsonObject("competition").get("name").getAsString();
            String competitionId = sportEvent.getAsJsonObject("sport_event_context").getAsJsonObject("competition").get("id").getAsString();
            String seasonName = sportEvent.getAsJsonObject("sport_event_context").getAsJsonObject("season").get("name").getAsString();

            BigDecimal probabilityHomeTeamWinner = sportEventProbabilities.get(0)
                    .getAsJsonObject().getAsJsonArray("markets").get(0)
                    .getAsJsonObject().getAsJsonArray("outcomes").get(0)
                    .getAsJsonObject().get("probability").getAsBigDecimal();

            BigDecimal probabilityAwayTeamWinner = sportEventProbabilities.get(0)
                    .getAsJsonObject().getAsJsonArray("markets").get(0)
                    .getAsJsonObject().getAsJsonArray("outcomes").get(1)
                    .getAsJsonObject().get("probability").getAsBigDecimal();

            BigDecimal probabilityDraw = sportEventProbabilities.get(0)
                    .getAsJsonObject().getAsJsonArray("markets").get(0)
                    .getAsJsonObject().getAsJsonArray("outcomes").get(2)
                    .getAsJsonObject().get("probability").getAsBigDecimal();

            JsonObject venue = sportEvent.getAsJsonObject("venue");
            String venueId = venue.get("id").getAsString();
            String venueName = venue.get("name").getAsString();
            Integer capacity = venue.get("capacity").getAsInt();
            String cityName = venue.get("city_name").getAsString();
            String countryName = venue.get("country_name").getAsString();
            String mapCoordinates = venue.get("map_coordinates").getAsString();
            String countryCode = venue.get("country_code").getAsString();

            Venue venue1 = new Venue(
                    venueId,
                    venueName,
                    capacity,
                    cityName,
                    countryName,
                    mapCoordinates,
                    countryCode,
                    List.of()
            );
            System.out.println(venue1);


            List<Competitor> competitors = new ArrayList<>();
            JsonArray competitorsArray = sportEvent.getAsJsonArray("competitors");
            for (JsonElement competitorElement : competitorsArray) {
                JsonObject competitorObject = competitorElement.getAsJsonObject();
                Competitor competitor = new Competitor();
                competitor.setId(competitorObject.get("id").getAsString());
                competitor.setName(competitorObject.get("name").getAsString());
                competitor.setCountry(competitorObject.get("country").getAsString());
                competitor.setCountry_code(competitorObject.get("country_code").getAsString());
                competitor.setAbbreviation(competitorObject.get("abbreviation").getAsString());
                competitor.setQualifier(competitorObject.get("qualifier").getAsString());
                competitor.setGender(competitorObject.get("gender").getAsString());
                competitors.add(competitor);
                System.out.println(competitor);
            }


            Event event = new Event(eventId,
                    startTime,
                    sportName,
                    competitionName,
                    competitionId,
                    seasonName,
                    venue1,
                    probabilityHomeTeamWinner,
                    probabilityDraw,
                    probabilityAwayTeamWinner,
                    competitors);


            System.out.println(event);
            conn.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}