package com.example.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.project.service.OpenAIService;
import com.example.project.vo.Member;
import com.example.project.vo.Rq;


@Controller
public class OpenAIController {
	
	private OpenAIService openAIService;
	private Rq rq;
	
	@Autowired
	public OpenAIController(OpenAIService openAIService, Rq rq) {
		this.openAIService = openAIService;
		this.rq = rq;
	}
	
	
	@RequestMapping("/usr/openai/chatbot")
	public String showChatBot() {	
		return "usr/openai/chatBot";
	}
	
	@RequestMapping("/usr/openai/getchatbot")
	@ResponseBody
	public String showChatBot(String userInstruct, String userInput) {
		
		Member member= rq.getLoginedMember();
		int memberId = member.getId();
		
		System.out.println(userInstruct);
		System.out.println(userInput);
		String response = openAIService.getResponseFromOpenAI(userInstruct,userInput);
		System.out.println(response);
		openAIService.setUserInputResponse(memberId,userInput,response);
		
		return response;
		
		
	}
	
	

}
