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
        
//        List<String> pastPrompt = loadUserInputResponse();
        JSONObject system = new JSONObject();
        JSONObject user = new JSONObject();
        JSONArray ja = new JSONArray();
        JSONObject body = new JSONObject();

//         Create request body는 Json 양식
//        if(pastPrompt.size()>2) {
//         body = "{ \"model\": \"gpt-3.5-turbo\", \"messages\": " 
//        				+ "["+"{\"role\": \"system\", \"content\": \""+userInstruct+"\"},"
//        				+pastPrompt.get(0) +","+pastPrompt.get(1) +","+ pastPrompt.get(2)+","
//        				+ "{\"role\": \"user\", \"content\": \""+prompt+"\"}]}";
//        }else {

//		  String body = "{ \"model\": \"gpt-3.5-turbo\", \"messages\": " +
//		  "[{\"role\": \"system\", \"content\": \""+userInstruct+"\"}," +
//		  "{\"role\": \"user\", \"content\": \""+prompt+"\"}]}";
//        }
		system.put("role","system");
		system.put("content",userInstruct );
		user.put("role","user");
		user.put("content", prompt);
		ja.put(system);
		ja.put(user);
	    body.put("model", "gpt-3.5-turbo");
	    body.put("messages", ja);
        

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
		
		String body ="{\"role\": \"user\", \"content\": \""  + userInput +"\"}" +","
				+ "{\"role\": \"assistant\", \"content\": \"" + response +"\"}"; 
		System.out.println(body);
		
		
		openAIRepository.setUserInputResponse(memberId,userInput, response,body);
		loadUserInputResponse();
		
	}
	
	// 최근 문답 3개 가져오기
	public List<String> loadUserInputResponse() {
		List<String> list = openAIRepository.loadUserInputResponse();
		 List<String> reversedList = new ArrayList<>(list);
		 Collections.reverse(reversedList);
		 //첫번째 요소는 가장 최근 요소로
//		 System.out.println(reversedList.get(0));

		return reversedList;
	}
}