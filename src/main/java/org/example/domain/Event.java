package org.example.domain;

import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;


@Getter
@ToString
public class Event {
    private String sport_event_id;
    private ZonedDateTime start_date;
    private String sport_name;
    private String competition_name;
    private String competition_id;
    private String season_name;
    private Venue venue;
    private BigDecimal probability_home_team_winner;
    private BigDecimal probability_draw;
    private BigDecimal probability_away_team_winner;
    private List<Competitor> competitors;
}
