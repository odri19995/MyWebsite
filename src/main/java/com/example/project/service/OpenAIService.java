package com.example.project.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.project.repository.OpenAIRepository;
import com.example.project.vo.OpenAIProfile;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class OpenAIService {
	private OpenAIRepository openAIRepository;
	
	@Autowired
	public OpenAIService(OpenAIRepository openAIRepository) {
		this.openAIRepository = openAIRepository;
	}

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
        
        List<String> pastUserPrompt = loadUserInput();
        List<String> pastAIPrompt = loadAIresponse();
        JSONObject system = new JSONObject();
        JSONObject user = new JSONObject();
        JSONObject assistant0 = new JSONObject();
        JSONObject user0 = new JSONObject();
        JSONObject assistant1 = new JSONObject();
        JSONObject user1 = new JSONObject();
        JSONObject assistant2 = new JSONObject();
        JSONObject user2 = new JSONObject();
        JSONArray ja = new JSONArray();
        JSONObject body = new JSONObject();
        

//         Create request body는 Json 양식
        if(pastUserPrompt.size()>2) {
        	
    		system.put("role","system");
    		system.put("content",userInstruct );
    		user0.put("role","user");
    		user0.put("content", pastUserPrompt.get(0));
    		assistant0.put("role","assistant");
    		assistant0.put("content", pastAIPrompt.get(0));
    		user1.put("role","user");
    		user1.put("content", pastUserPrompt.get(1));
    		assistant1.put("role","assistant");
    		assistant1.put("content", pastAIPrompt.get(1));
    		user2.put("role","user");
    		user2.put("content", pastUserPrompt.get(2));
    		assistant2.put("role","assistant");
    		assistant2.put("content", pastAIPrompt.get(2));
    		user.put("role","user");
    		user.put("content", prompt);
    		ja.put(system);
    		ja.put(user0);
    		ja.put(assistant0);
    		ja.put(user1);
    		ja.put(assistant1);
    		ja.put(user2);
    		ja.put(assistant2);
    		ja.put(user);
    	    body.put("model", "gpt-3.5-turbo");
    	    body.put("messages", ja);
    	}else {

    		system.put("role","system");
    		system.put("content",userInstruct );
    		user.put("role","user");
    		user.put("content", prompt);
    		ja.put(system);
    		ja.put(user);
    	    body.put("model", "gpt-3.5-turbo");
    	    body.put("messages", ja);
       }

        

        // Create entity
        HttpEntity<String> requestEntity = new HttpEntity<>(body.toString(), headers);
        System.out.println(requestEntity.getBody());
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

	public void setUserInputResponse(int memberId, String userInput, String response) {		
		openAIRepository.setUserInputResponse(memberId,userInput, response);
		
	}
	// 이전 게시글 번호 가져오기
	
	public int loadTrunId() {
		int recentTrunId = openAIRepository.loadTrunId();
		return recentTrunId;
	}
	
	
	
	
	// 최근 문답 3개 가져오기
	public List<String> loadUserInput() {
		List<String> list = openAIRepository.loadUserInput();
		 List<String> reversedList = new ArrayList<>(list);
		 Collections.reverse(reversedList);
		 //첫번째 요소는 가장 최근 요소로

		return reversedList;
	}
	public List<String> loadAIresponse() {
		List<String> list = openAIRepository.loadAIresponse();
		 List<String> reversedList = new ArrayList<>(list);
		 Collections.reverse(reversedList);
		 //첫번째 요소는 가장 최근 요소로

		return reversedList;
	}
	
	
	
}