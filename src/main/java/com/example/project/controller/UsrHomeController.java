package com.example.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UsrHomeController {
	

	@RequestMapping("/usr/home/main")
	public String showMain() {
		return "usr/home/main";
	}
	
	
	@RequestMapping("/")
	public String baseRoot() {
		return "redirect:/usr/home/main";
	}
	
	
	
}