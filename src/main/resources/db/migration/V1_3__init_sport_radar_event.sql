create table event
(
    sport_event_id               VARCHAR(32)    NOT NULL,
    start_date                   TIMESTAMP WITH TIME ZONE,
    sport_name                   VARCHAR(32)    NOT NULL,
    competition_name             varchar(32)    NOT NULL,
    competition_id               VARCHAR(32)    NOT NULL,
    season_name                  VARCHAR(32)    NOT NULL,
    venue_id                     VARCHAR(32),
    probability_home_team_winner NUMERIC(19, 2) NOT NULL,
    probability_draw             NUMERIC(19, 2) NOT NULL,
    probability_away_team_winner NUMERIC(19, 2) NOT NULL,
    competitor_id                VARCHAR(32)    NOT NULL,
    PRIMARY KEY (sport_event_id),
    CONSTRAINT fk_event_venue
        FOREIGN KEY (venue_id)
            REFERENCES venue (id)
);