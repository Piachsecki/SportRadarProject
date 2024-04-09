package org.example;

public class Main {
    public static void main(String[] args) {
        MatchFinder matchFinder = new MatchFinder("./src/main/resources/config.json");
        matchFinder.highestProbableValues(matchFinder.getEvents(), 20);
    }
}