package com.example.project.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OpenAIService {

    @Value("${openai.api.key}")
    private String openAIKey;

    public String getResponseFromOpenAI(String prompt) {
        RestTemplate restTemplate = new RestTemplate();

        // OpenAI API endpoint
        String url = "https://api.openai.com/v1/chat/completions";

        // Create headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(openAIKey);  // Set OpenAI API key

        // Create request body
        String body = "{ \"model\": \"gpt-3.5-turbo\", \"messages\": [{\"role\": \"user\", \"content\": \""+prompt+"\"}]}";

        // Create entity
        HttpEntity<String> requestEntity = new HttpEntity<>(body, headers);

        // Send request
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
		
	
        return response.getBody();
    }
}