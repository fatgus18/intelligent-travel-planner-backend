package com.travelplanner.planner_api;

import java.util.List;

public class GeminiDto {
    
    public static record GeminiRequest(List<Content> contents) {}

    public static record Content(List<Part> parts) {}

    public static record Part(String text) {}

    public static record GeminiResponse(List<Candidate> candidates) {}

    public static record Candidate(Content content) {}
}
