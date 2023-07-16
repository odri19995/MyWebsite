package com.example.project.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.project.service.ArticleService;
import com.example.project.service.OpenAIService;
import com.example.project.util.Util;
import com.example.project.vo.Member;
import com.example.project.vo.Rq;


@Controller
public class OpenAIController {
	
	private OpenAIService openAIService;
	private ArticleService articleService;
	private Rq rq;
	ArrayList<String> userInputs = new ArrayList<>();
	ArrayList<String> botResponses = new ArrayList<>();

	
	@Autowired
	public OpenAIController(OpenAIService openAIService, ArticleService articleService, Rq rq) {
		this.openAIService = openAIService;
		this.articleService = articleService;
		this.rq = rq;
		userInputs = new ArrayList<>();
		botResponses = new ArrayList<>();
	}
	
	
	@RequestMapping("/usr/openai/chatbot")
	public String showChatBot() {
		userInputs.clear();
		botResponses.clear();
		return "usr/openai/chatBot";
	}
	
	@RequestMapping("/usr/openai/getchatbot")
	@ResponseBody
	public String showChatBot(String userInstruct, String userInput) {
		

		
		System.out.println(userInstruct);
		System.out.println(userInput);
		String response = openAIService.getResponseFromOpenAI(userInstruct,userInput);
		System.out.println(response);
		
		userInputs.add(userInput);
		botResponses.add(response);
		
		return response;
		
		
	}
	
	@RequestMapping("/usr/openai/doWrite")
	@ResponseBody
	public String doWrite(String title) {
		if (Util.empty(title)) {
			return Util.jsHistoryBack("제목을 입력해주세요");
		}
		
		Member member= rq.getLoginedMember();
		int memberId = member.getId();

		
		articleService.writeArticle(title,memberId );
		int id = articleService.getLastInsertId(); 
		int n = userInputs.size();
		for(int i = 0; i<n ;i++) {
			openAIService.setUserInputResponse(memberId,id,userInputs.get(i),botResponses.get(i));
		}
		return Util.jsReplace(Util.f("%d번 게시물이 생성되었습니다", id), "/usr/openai/chatbot");
	}
	
	

}
