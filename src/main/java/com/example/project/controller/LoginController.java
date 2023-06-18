package com.example.project.controller;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
			HttpClient client = HttpClient.newHttpClient();

			HttpRequest request = HttpRequest.newBuilder()
			    .uri(URI.create("https://kauth.kakao.com/oauth/logout?client_id=" + "ef68c2f02e7c9f1813716129dfbdccf5" + "&logout_redirect_uri=" + "http://localhost:8081/usr/home/main"))
			    .GET()
			    .build();

			HttpResponse<String> response;
			try {
				response = client.send(request, HttpResponse.BodyHandlers.ofString());
				System.out.println("gjhgjh : " + response);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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