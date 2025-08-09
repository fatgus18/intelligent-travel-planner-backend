package com.travelplanner.planner_api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/itineraries")
public class ItineraryController {

    @Autowired
    private ItineraryService itineraryService;

    @GetMapping
    public ResponseEntity<List<Itinerary>> getAllItineraries() {
        return ResponseEntity.ok(itineraryService.getAllItineraries());
    }
    
    @PostMapping
    public ResponseEntity<Itinerary> generateItinerary(@RequestBody ItineraryRequest request) {
        Itinerary itinerary = itineraryService.generateItinerary(request.destination(), request.prompt());
        return ResponseEntity.ok(itinerary);
    }
}

record ItineraryRequest(String destination, String prompt) {}
