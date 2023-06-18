package com.example.project.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.example.project.service.MemberService;
import com.example.project.util.Util;
import com.example.project.vo.Member;
import com.example.project.vo.Rq;

@Controller
@RequestMapping("/usr")
public class LoginController {
	
	private MemberService memberService;
	private Rq rq;
	
	
	@Autowired
	public LoginController(MemberService memberService, Rq rq) {
		this.memberService = memberService;
		this.rq = rq;
	}
	
	@GetMapping("/login")
	public String loginForm() {
		return "usr/member/loginForm";
	}
	
	@GetMapping("/logout")
	@ResponseBody
	public String logout(HttpSession session) {
		//kakao logout
		
		if(session.getAttribute("kakaologin") != null) {
			RestTemplate rt = new RestTemplate();
			
			// HttpHeader 오브젝트 생성
			HttpHeaders headers = new HttpHeaders();
			headers.add("Authorization", "Bearer "+session.getAttribute("kakaologin"));
			
			//HttpHeader를 하나의 오브젝트에 담기
			HttpEntity<MultiValueMap<String,String>> kakaoLogoutRequest =
				new HttpEntity<>(headers);
			
			// Http 요청하기 - post방식으로 -그리고 response변수의 응답을 받음
			ResponseEntity<String> response = rt.exchange(
					"https://kapi.kakao.com/v1/user/unlink",
					HttpMethod.POST,
					kakaoLogoutRequest,
					String.class				
			);
			
			System.out.println(response.getBody());
			session.removeAttribute("kakaologin");
		}
		
		
		rq.logout();
		session.invalidate();
		return Util.jsReplace("정상적으로 로그아웃 되었습니다", "/");
	}

	@PostMapping("/login")
	@ResponseBody
	public String login(String id, String pwd, boolean rememberId,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		if (rq.getLoginedMemberId() != 0) {
			return Util.jsHistoryBack("로그아웃 후 이용해주세요");
		}
		
		if (Util.empty(id)) {
			return Util.jsHistoryBack("아이디를 입력해주세요");
		}
		if (Util.empty(pwd)) {
			return Util.jsHistoryBack("비밀번호를 입력해주세요");
		}
		
		Member member = memberService.getMemberByLoginId(id);
		
		
		if (member == null) {
			return Util.jsHistoryBack(Util.f("%s은(는) 존재하지 않는 아이디입니다", id));
		}
		
		if (member.getLoginPw().equals(pwd) == false) {
			return Util.jsHistoryBack("비밀번호가 일치하지 않습니다");
		}
		rq.login(member);
		
		// 2-2. id와 pwd가 일치하면,
		HttpSession session = request.getSession();
		
		// 세션 객체에 id를 저장		
		session.setAttribute("id",id);
		
		if(rememberId) {
		//     1. 쿠키를 생성
			Cookie cookie = new Cookie("id", id); // ctrl+shift+o 자동 import
//		       2. 응답에 저장
			response.addCookie(cookie);
		} else {
// 		       1. 쿠키를 삭제
			Cookie cookie = new Cookie("id", id); // ctrl+shift+o 자동 import
			cookie.setMaxAge(0); // 쿠키를 삭제
//		       2. 응답에 저장
			response.addCookie(cookie);
		}
//		3. 홈으로 이동
		return Util.jsReplace(Util.f("%s 회원님 환영합니다~!", member.getNickname()), "/");
	}
	
	
}