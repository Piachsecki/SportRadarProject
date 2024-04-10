package org.example.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;



@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table
@Entity
public class Event {

    @EqualsAndHashCode.Include
    @Id
    @Column(name = "sport_event_id")
    private String sport_event_id;


    @Column(name = "start_date")
    private ZonedDateTime start_date;

    @Column(name = "sport_name")
    private String sport_name;

    @Column(name = "competition_name")
    private String competition_name;

    @Column(name = "competition_id")
    private String competition_id;

    @Column(name = "season_name")
    private String season_name;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id", unique = true)
    private Venue venue;

    @Column(name = "probability_home_team_winner")
    private BigDecimal probability_home_team_winner;

    @Column(name = "probability_draw")
    private BigDecimal probability_draw;

    @Column(name = "probability_away_team_winner")
    private BigDecimal probability_away_team_winner;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "event", cascade = CascadeType.REMOVE)
    private List<Competitor> competitors;


}
