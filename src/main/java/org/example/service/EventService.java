package org.example.service;

import org.example.domain.Event;
import org.example.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EventService {
    private final EventRepository eventRepository;
    private final CompetitorService competitorService;
    private final VenueService venueService;

    @Autowired
    public EventService(
            EventRepository eventRepository,
            CompetitorService competitorService,
            VenueService venueService) {
        this.eventRepository = eventRepository;
        this.competitorService = competitorService;
        this.venueService = venueService;
    }

    @Transactional
    public Event addEvent(Event event) {
        try{

        if (event.getVenue() == null || event.getVenue().getId() == null) {
            throw new IllegalArgumentException("Venue ID cannot be null");
        }
        venueService.addVenue(event.getVenue());
        competitorService.addCompetitor(event.getCompetitors().get(0));
        competitorService.addCompetitor(event.getCompetitors().get(1));
        return eventRepository.save(event);


    } catch (Exception e) {
        // Log the exception or handle it as needed
        e.printStackTrace();
        // If you want to continue program execution after the exception, return null or handle it accordingly
        return null;
    }
    }
    public List<Event> getEventsWithTheBiggestProbability(int numberOfExpectedMatches){
        return eventRepository.findTheHighestProbableWins(numberOfExpectedMatches);
    }


}
