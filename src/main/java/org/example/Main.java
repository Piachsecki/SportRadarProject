package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
//        MatchFinder matchFinder = new MatchFinder();
//        List<Event> events = matchFinder.highestProbableValues(matchFinder.getEvents(), 20);
//        events.forEach(PrintingUtil::printTheResults);

    }
}