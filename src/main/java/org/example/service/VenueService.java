package org.example.service;

import org.example.domain.Venue;
import org.example.repository.VenueRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class VenueService {
    private final VenueRepository venueRepository;

    public VenueService(VenueRepository venueRepository) {
        this.venueRepository = venueRepository;
    }

    public Venue addVenue(Venue venue){
        return venueRepository.save(venue);
    }
}
