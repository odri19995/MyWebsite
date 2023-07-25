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
	ArrayList<String> userInputs = new ArrayList<>();
	ArrayList<String> botResponses = new ArrayList<>();
	
	@Autowired
	public OpenAIService(OpenAIRepository openAIRepository) {
		this.openAIRepository = openAIRepository;
		userInputs = new ArrayList<>();
		botResponses = new ArrayList<>();
	}

    @Value("${openai.api.key}")
    private String openAIKey;

    public String getResponseFromOpenAI(String userInstruct, String prompt, int memberId) {
        RestTemplate restTemplate = new RestTemplate();

        // OpenAI API endpoint
        String url = "https://api.openai.com/v1/chat/completions";

        // Create headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(openAIKey);  // Set OpenAI API key
        

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
        if(botResponses.size()>2) {
        	
    		system.put("role","system");
    		system.put("content",userInstruct );
    		user0.put("role","user");
    		user0.put("content", userInputs.get(botResponses.size()-3));
    		assistant0.put("role","assistant");
    		assistant0.put("content", botResponses.get(botResponses.size()-3));
    		user1.put("role","user");
    		user1.put("content", userInputs.get(botResponses.size()-2));
    		assistant1.put("role","assistant");
    		assistant1.put("content", botResponses.get(botResponses.size()-2));
    		user2.put("role","user");
    		user2.put("content", userInputs.get(botResponses.size()-1));
    		assistant2.put("role","assistant");
    		assistant2.put("content", botResponses.get(botResponses.size()-1));
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
    	    userInputs.remove(0);
    	    botResponses.remove(0);
    	}else {

    		system.put("role","system");
    		system.put("content",userInstruct );
    		user.put("role","user");
    		user.put("content", prompt);
    		ja.put(system);
    		ja.put(user);
    	    body.put("model", "gpt-3.5-turbo");
    	    body.put("messages", ja);
    	    System.out.println("이전 대화 미적용상태");
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
//  DB에 추가하는 메서드
	public void setUserInputResponse(int memberId,int articleId, String userInput, String response) {		
		openAIRepository.setUserInputResponse(memberId,articleId, userInput, response);
		
	}

	public void passArrays(ArrayList<String> userInputss, ArrayList<String> botResponsess) {
		botResponses =botResponsess;
		userInputs = userInputss;
		
	}
	
	
	
}