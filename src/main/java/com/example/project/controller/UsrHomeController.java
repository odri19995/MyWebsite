package com.example.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UsrHomeController {
//	http://localhost:8081/usr/home/main
	@RequestMapping("/usr/home/main") //responsebody 내가 직접 응답을 못함
	public String showMain() {
		return "usr/home/main";
	}
	
	@RequestMapping("/")
	public String showRoot() {
		return "redirect:/usr/home/main";
	}
	
	@RequestMapping("/usr/kakao/kakao")
	public String kakao() {
		return "usr/kakao/kakao";
	}
	
	@RequestMapping("/usr/kakao/kakaokey")
	@ResponseBody
	public String kakaoKeySet() {
		String kakaoKey = "8ac38a303b92b2b32c95859ac68ee2bb";
		return kakaoKey;
	}
	
}