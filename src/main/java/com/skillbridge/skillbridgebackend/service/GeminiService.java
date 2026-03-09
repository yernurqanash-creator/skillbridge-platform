package com.skillbridge.skillbridgebackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class GeminiService {

    @Value("${gemini.api-key}")
    private String apiKey;

    private final WebClient.Builder webClientBuilder;

    public String extractSkills(String cvText) {

        String prompt = """
                Extract ONLY IT technical skills.
                Return strictly JSON array format.
                Example:
                ["java","spring","docker"]
                Text:
                """ + cvText;

        Map<String, Object> requestBody = Map.of(
                "contents", new Object[]{
                        Map.of("parts", new Object[]{
                                Map.of("text", prompt)
                        })
                }
        );

        return webClientBuilder.build()
                .post()
                .uri("https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent?key=" + apiKey)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}