package org.example.service;

import org.example.domain.Competitor;
import org.example.repository.CompetitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompetitorService {
    private final CompetitorRepository competitorRepository;


    @Autowired
    public CompetitorService(CompetitorRepository competitorRepository) {
        this.competitorRepository = competitorRepository;
    }

    public Competitor addCompetitor(Competitor competitor) {
        return competitorRepository.save(competitor);
    }


}
