package org.example.controller;


import org.example.MatchFinder;
import org.example.domain.Event;
import org.example.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/results")
public class ResultController {
    private MatchFinder matchFinder;
    private EventService eventService;


    @Autowired
    public ResultController(MatchFinder matchFinder, EventService eventService) {
        this.eventService = eventService;
        this.matchFinder = matchFinder;
    }


    @GetMapping(value = "/get/{fileName}/{numberOfResults}")
    public ResponseEntity<List<Event>> getResults(
            @PathVariable("fileName") String fileName,
            @PathVariable("numberOfResults") Integer numberOfResults) {

        matchFinder.init(String.valueOf("src/main/resources/static/" + fileName));

        List<Event> result = eventService.getEventsWithTheBiggestProbability(numberOfResults);

        return ResponseEntity.ok(result);
    }


}
