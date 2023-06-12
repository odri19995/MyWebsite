package com.example.project.controller;



import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;



@Controller // ctrl+shift+o 자동 임포트 
public class RegisterController {
//	@RequestMapping(value="/register/add", method=RequestMethod.GET) // 신규회원 가입
	@GetMapping("/register/add") // 4.3부터 추가
	public String register() {
		return "usr/member/registerForm";  // WEB-INF/views/registerForm.jsp
	}
	


	
}