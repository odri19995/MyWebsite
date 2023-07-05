package com.example.project.controller;



import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.project.service.ArticleService;
import com.example.project.vo.Article;

@Controller
@RequestMapping("/usr/board")
public class BoardController {
	private ArticleService articleService;
	
	@Autowired
	public BoardController(ArticleService articleService) {
		this.articleService = articleService;
	}
	
	
	@GetMapping("/list")
	public String list(HttpServletRequest request, Model model) {
		if(!loginCheck(request))
			return "redirect:/usr/login";  // 로그인을 안했으면 로그인 화면으로 이동
		
		List<Article> articles = articleService.getArticles();
		
		model.addAttribute("articles", articles);
		
		return "usr/home/boardList"; // 로그인을 한 상태이면, 게시판 화면으로 이동
	}
	

	private boolean loginCheck(HttpServletRequest request) {
		// 1. 세션을 얻어서
		HttpSession session = request.getSession();
		// 2. 세션에 id가 있는지 확인, 있으면 true를 반환
		return session.getAttribute("id")!=null;
	}
}