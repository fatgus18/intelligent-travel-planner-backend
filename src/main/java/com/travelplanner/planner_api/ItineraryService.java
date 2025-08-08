package com.travelplanner.planner_api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ItineraryService{

    @Autowired
    private ItineraryRepository itineraryRepository;

    @Value("${ai.api.key}")
    private String apiKey;

    public Itinerary generateItinerary(String destination, String userPrompt){


        String aiResponse = " ";

        Itinerary itinerary = new Itinerary();
        itinerary.setDestination(destination);
        itinerary.setUserPrompt(userPrompt);
        itinerary.setGeneratedContent(aiResponse);

        return itineraryRepository.save(itinerary);
    }

    public List<Itinerary>getAllItineraries(){
        return itineraryRepository.findAll();
    }
}

