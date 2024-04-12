package org.example.service;

import org.example.domain.Event;
import org.example.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        venueService.addVenue(event.getVenue());
        competitorService.addCompetitor(event.getCompetitors().get(0));
        competitorService.addCompetitor(event.getCompetitors().get(1));
        return eventRepository.save(event);
    }


}
