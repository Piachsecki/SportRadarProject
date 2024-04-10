package org.example.util;

import lombok.experimental.UtilityClass;
import org.example.domain.Event;

import java.math.BigDecimal;
import java.util.Objects;

@UtilityClass
public class PrintingUtil {
    public static void printTheResults(Event event) {
        if (Objects.nonNull(event))
            System.out.println("Start date: " + event.getStart_date() + ",");
        System.out.println(event.getCompetitors().get(0).getName() + " vs " + event.getCompetitors().get(1).getName() + ",");

        if (Objects.nonNull(event.getVenue()))
            System.out.println("Venue: " + event.getVenue().getName() + ",");
        else
            System.out.println("Venue: " + "Not set up yet");
        System.out.println("Highest probable result: " + drawOrHomeWin(event.getProbability_away_team_winner(), event.getProbability_home_team_winner()));
        System.out.println();
    }

    private static String drawOrHomeWin(BigDecimal probability_away_team_winner, BigDecimal probability_home_team_winner) {
        if (probability_home_team_winner.compareTo(probability_away_team_winner) > 0) {
            return String.format("HOME_TEAM_WIN (%s)", probability_home_team_winner);
        } else if (probability_home_team_winner.compareTo(probability_away_team_winner) < 0) {
            return String.format("AWAY_TEAM_WIN (%s)", probability_home_team_winner);
        }
        return String.format("DRAW (%s)", BigDecimal.valueOf(100).subtract(probability_away_team_winner).subtract(probability_home_team_winner));


    }


}
