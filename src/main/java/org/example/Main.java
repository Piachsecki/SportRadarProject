package org.example;

import org.example.domain.Event;
import org.example.util.PrintingUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
        MatchFinder matchFinder = new MatchFinder("./src/main/resources/static/config.json");
        List<Event> events = matchFinder.highestProbableValues(matchFinder.getEvents(), 20);
        events.forEach(PrintingUtil::printTheResults);

    }
}