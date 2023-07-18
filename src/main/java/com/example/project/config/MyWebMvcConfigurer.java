package com.example.project.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.project.interceptor.BeforeActionInterceptor;
import com.example.project.interceptor.NeedLoginInterceptor;
import com.example.project.interceptor.NeedLogoutInterceptor;

@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer {

	private BeforeActionInterceptor beforeActionInterceptor;
	private NeedLoginInterceptor needLoginInterceptor;
	private NeedLogoutInterceptor needLogoutInterceptor;

	@Autowired
	public MyWebMvcConfigurer(BeforeActionInterceptor beforeActionInterceptor,
			NeedLoginInterceptor needLoginInterceptor, NeedLogoutInterceptor needLogoutInterceptor) {
		this.beforeActionInterceptor = beforeActionInterceptor;
		this.needLoginInterceptor = needLoginInterceptor;
		this.needLogoutInterceptor = needLogoutInterceptor;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//모든 요청을 받는다.
		registry.addInterceptor(beforeActionInterceptor).addPathPatterns("/**").excludePathPatterns("/resource/**");

		registry.addInterceptor(needLoginInterceptor).addPathPatterns("/usr/article/list")
		.addPathPatterns("/usr/article/detail")
		.addPathPatterns("/usr/member/checkPassword").addPathPatterns("/usr/member/doCheckPassword")
		.addPathPatterns("/usr/member/doModify").addPathPatterns("/usr/member/passwordModify")
		.addPathPatterns("/usr/member/doPasswordModify").addPathPatterns("/usr/logout")
		.addPathPatterns("/usr/member/myPage").addPathPatterns("/usr/member/checkPassword")
		.addPathPatterns("/usr/member/doCheckPassword")
		.addPathPatterns("/usr/openai/chatbot").addPathPatterns("/usr/openai/getchatbot")
		.addPathPatterns("/usr/openai/doWrite");
		
		registry.addInterceptor(needLogoutInterceptor).addPathPatterns("/usr/login").addPathPatterns("/usr/kakao/kakaoLogin")
		.addPathPatterns("/usr/member/doJoin").addPathPatterns("/usr/member/join")
		
		;
	}

}