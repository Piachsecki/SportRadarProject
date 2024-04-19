package org.example.repository;

import org.example.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, String> {


    @Query(value = "SELECT * FROM Event ORDER BY GREATEST(probability_draw, probability_home_team_winner, probability_away_team_winner) DESC LIMIT :numberOfExpectedMatches", nativeQuery = true)
    List<Event> findTheHighestProbableWins(int numberOfExpectedMatches);

}
