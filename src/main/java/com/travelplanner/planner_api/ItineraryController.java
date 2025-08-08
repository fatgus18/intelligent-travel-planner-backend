package com.travelplanner.planner_api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("api/itineraries")
public class ItineraryController{

    @Autowired
    private ItineraryService itineraryService;

    @PostMapping
    public ResponseEntity<List<Itinerary>> getAllItineraries(){
        return ResponseEntity.ok(itineraryService.getAllItineraries());
    }    
}

record ItineraryRequest(String destination, String prompt){}
