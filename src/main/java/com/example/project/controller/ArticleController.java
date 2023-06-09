package com.example.project.controller;



import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.project.service.ArticleService;
import com.example.project.vo.Article;

@Controller
@RequestMapping("/usr/article")
public class ArticleController {
	private ArticleService articleService;
	
	@Autowired
	public ArticleController(ArticleService articleService) {
		this.articleService = articleService;
	}
	
	
	@GetMapping("/list")
	public String list(HttpServletRequest request, Model model) {
		if(!loginCheck(request))
			return "redirect:/usr/login";  // 로그인을 안했으면 로그인 화면으로 이동
		
		List<Article> articles = articleService.getArticles();
		
		model.addAttribute("articles", articles);
		
		return "usr/article/list"; // 로그인을 한 상태이면, 게시판 화면으로 이동
	}
	
	@GetMapping("/detail")
	@ResponseBody
	public String showDetail(Model model, int id) {
		
		Article[] articles = articleService.getForPrintArticles(id);
		Article article;
		List<String> userMessages = new ArrayList<String>();
		List<String> responses = new ArrayList<String>();
		model.addAttribute("articles", articles);
		for (int i = 0 ; i < articles.length; i++) {
			article=articles[i];
			userMessages.add(article.getUserMessage());
			responses.add(article.getResponse());	
		}
		return responses.get(1);
	}
	

	private boolean loginCheck(HttpServletRequest request) {
		// 1. 세션을 얻어서
		HttpSession session = request.getSession();
		// 2. 세션에 id가 있는지 확인, 있으면 true를 반환
		return session.getAttribute("id")!=null;
	}
}