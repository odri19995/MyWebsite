package com.example.project.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.project.vo.KakaoProfile;
import com.example.project.vo.OpenAIProfile;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class OpenAIService {

    @Value("${openai.api.key}")
    private String openAIKey;

    public String getResponseFromOpenAI(String userInstruct, String prompt) {
        RestTemplate restTemplate = new RestTemplate();

        // OpenAI API endpoint
        String url = "https://api.openai.com/v1/chat/completions";

        // Create headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(openAIKey);  // Set OpenAI API key

        // Create request body는 Json 양식
        String body = "{ \"model\": \"gpt-3.5-turbo\", \"messages\": " 
        				+ "[{\"role\": \"system\", \"content\": \""+userInstruct+"\"}," 
        				+ "{\"role\": \"user\", \"content\": \""+prompt+"\"}]}";
        

        // Create entity
        HttpEntity<String> requestEntity = new HttpEntity<>(body, headers);

        // Send request
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
        
        ObjectMapper objectMapper = new ObjectMapper();
        OpenAIProfile openAIProfile = new OpenAIProfile();
        
        try {
        	openAIProfile = objectMapper.readValue(response.getBody(),OpenAIProfile.class);
        	System.out.println(openAIProfile);
        } catch (JsonProcessingException e) {
        	e.printStackTrace();
        }
       String reply =openAIProfile.getChoices().get(0).getMessage().getContent();
        		
	
        return reply;
    }
}