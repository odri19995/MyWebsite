package com.example.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.project.service.OpenAIService;
import com.example.project.util.Util;
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
	public String showChatBot(String userInput) {
		
		if (Util.empty(userInput)) {
			return """
					<script>
						alert("내용을 입력해주세요");
					</script>
					""";
		}

		
		System.out.println(userInput);
		String response = openAIService.getResponseFromOpenAI(userInput);
		System.out.println(response);
		
		return response;
		
		
	}

}
