package com.roadmanagement.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;

@Service
public class GeographyAIService {

    @Value("${groq.api.key}")
    private String groqApiKey;

    @Value("${groq.model}")
    private String model;

    @Value("${groq.temperature}")
    private double temperature;

    private final WebClient webClient = WebClient.builder()
            .baseUrl("https://api.groq.com/openai/v1")
            .defaultHeader("Content-Type", "application/json")
            .build();

    public String getAnswer(String question) {

        String prompt =
                "You are an AI assistant for a Road Management System. " +
                        "Answer strictly from a geographical and infrastructure perspective. " +
                        "Focus on terrain, road connectivity, disaster risk, and traffic planning.\n\n" +
                        "Question: " + question;

        // Build request body properly
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", model);
        requestBody.put("temperature", temperature);
        requestBody.put("stream", false);

        List<Map<String, String>> messages = new ArrayList<>();
        messages.add(Map.of("role", "user", "content", prompt));

        requestBody.put("messages", messages);

        try {

            Map response = webClient.post()
                    .uri("/chat/completions")
                    .header("Authorization", "Bearer " + groqApiKey)
                    .bodyValue(requestBody)
                    .retrieve()
                    .bodyToMono(Map.class)
                    .block();

            if (response == null || !response.containsKey("choices")) {
                return "No valid response from AI.";
            }

            List choices = (List) response.get("choices");
            Map firstChoice = (Map) choices.get(0);
            Map message = (Map) firstChoice.get("message");

            return message.get("content").toString();

        } catch (WebClientResponseException e) {
            return "Groq API Error: " + e.getStatusCode() + " - " + e.getResponseBodyAsString();
        } catch (Exception e) {
            return "Unexpected error: " + e.getMessage();
        }
    }
}