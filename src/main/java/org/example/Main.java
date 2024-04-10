package org.example;

import org.example.domain.Event;
import org.example.util.PrintingUtil;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        MatchFinder matchFinder = new MatchFinder("./src/main/resources/config.json");
        List<Event> events = matchFinder.highestProbableValues(matchFinder.getEvents(), 20);
        events.forEach(PrintingUtil::printTheResults);
    }
}