package com.example.project.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.example.project.vo.KakaoProfile;
import com.example.project.vo.OAuthToken;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class UsrHomeController {
//	http://localhost:8081/usr/home/main
//	@RequestMapping("/usr/home/main") //responsebody 내가 직접 응답을 못함
//	public String showMain(String code) {
//		return "usr/home/main";
//	}
	@GetMapping("/usr/home/main")
	public @ResponseBody String kakaoCallback(String code) {
		
		
		RestTemplate rt = new RestTemplate();
		
		
		// HttpHeader 오브젝트 생성
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		
		//HttpBody 오브젝트 생성
		MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "authorization_code");
		params.add("client_id", "ef68c2f02e7c9f1813716129dfbdccf5");
		params.add("redirect_uri", "http://localhost:8081/usr/home/main");
		params.add("code", code);
		
		//HttpHeader와 HttpBody를 하나의 오브젝트에 담기
		HttpEntity<MultiValueMap<String,String>> kakaoTokenRequest =
			new HttpEntity<>(params,headers);
		
		// Http 요청하기 - post방식으로 -그리고 response변수의 응답을 받음
		ResponseEntity<String> response = rt.exchange(
				"https://kauth.kakao.com/oauth/token",
				HttpMethod.POST,
				kakaoTokenRequest,
				String.class				
		);
		
		// GSon, Json Simple, ObjectMapper();
		
		ObjectMapper objectMapper = new ObjectMapper();
		OAuthToken oauthToken = null;
		
		try {
			oauthToken = objectMapper.readValue(response.getBody(),OAuthToken.class);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("카카오 엑세스 토큰 : " + oauthToken.getAccess_token());
		
		RestTemplate rt2 = new RestTemplate();
		
		
		// HttpHeader 오브젝트 생성
		HttpHeaders headers2 = new HttpHeaders();
		headers2.add("Authorization", "Bearer "+oauthToken.getAccess_token());
		headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
				
		//HttpHeader와 HttpBody를 하나의 오브젝트에 담기
		HttpEntity<MultiValueMap<String,String>> kakaoProfileRequest =
			new HttpEntity<>(headers2);
		
		// Http 요청하기 - post방식으로 -그리고 response변수의 응답을 받음
		ResponseEntity<String> response2 = rt2.exchange(
				"https://kapi.kakao.com/v2/user/me",
				HttpMethod.POST,
				kakaoProfileRequest,
				String.class				
		);
		System.out.println(response2.getBody());
		
		ObjectMapper objectMapper2 = new ObjectMapper();
		KakaoProfile kakaoProfile = null;
		
		try {
			kakaoProfile = objectMapper2.readValue(response2.getBody(),KakaoProfile.class);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("카카오 아이디 번호 : " +kakaoProfile.getId());
		System.out.println("카카오 사용자 : " +kakaoProfile.getProperties().getNickname());

		

		
		return "kakao 토큰 요청 완료  :" + response2.getBody();
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