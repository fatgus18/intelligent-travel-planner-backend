package com.travelplanner.planner_api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ItineraryService {

    @Autowired
    private ItineraryRepository itineraryRepository;

    @Value("${ai.api.key}")
    private String apiKey;

    public Itinerary generateItinerary(String destination, String userPrompt) {
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash-latest:generateContent?key=" + apiKey;

        String fullPrompt = String.format(
            "Create a detailed travel itinerary for a trip to %s. " +
            "The user's preferences are: '%s'. " +
            "Provide a day-by-day plan with specific, realistic suggestions for activities, local food, and transportation. " +
            "Format the output clearly with headings for each day.",
            destination, userPrompt
        );

        GeminiDto.Part part = new GeminiDto.Part(fullPrompt);
        GeminiDto.Content content = new GeminiDto.Content(List.of(part));
        GeminiDto.GeminiRequest requestPayload = new GeminiDto.GeminiRequest(List.of(content));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<GeminiDto.GeminiRequest> entity = new HttpEntity<>(requestPayload, headers);

        GeminiDto.GeminiResponse geminiResponse = restTemplate.postForObject(apiUrl, entity, GeminiDto.GeminiResponse.class);

        String aiResponse = "No response from AI.";
        if (geminiResponse != null && !geminiResponse.candidates().isEmpty()) {
            aiResponse = geminiResponse.candidates().get(0).content().parts().get(0).text();
        }
        
        Itinerary itinerary = new Itinerary();
        itinerary.setDestination(destination);
        itinerary.setUserPrompt(userPrompt);
        itinerary.setGeneratedContent(aiResponse);

        return itineraryRepository.save(itinerary);
    }
    
    public List<Itinerary> getAllItineraries() {
        return itineraryRepository.findAll();
    }
}