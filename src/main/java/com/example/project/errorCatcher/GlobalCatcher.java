package com.example.project.errorCatcher;

import java.io.FileNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

//	@ControllerAdvice("com.example.project.controller") // 지정된 패키지에만 적용
	@ControllerAdvice // 모든 패키지에 적용
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) //200 ->500
	public class GlobalCatcher {
		@ExceptionHandler({NullPointerException.class, FileNotFoundException.class})
		public String catcher2(Exception ex, Model m) {
			m.addAttribute("ex", ex);
			return "error/error";
		}
	
		@ExceptionHandler(Exception.class)
		public String catcher(Exception ex, Model m) {
			m.addAttribute("ex", ex);
	
			return "error/error";
		}
	}