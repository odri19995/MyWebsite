package com.example.project.vo;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.example.project.service.MemberService;
import com.example.project.util.Util;

import lombok.Getter;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Rq {
	
	@Getter
	private int loginedMemberId;
	@Getter
	private Member loginedMember;
	private HttpServletRequest req;
	private HttpServletResponse resp;
	private HttpSession httpSession;

	public Rq(HttpServletRequest req, HttpServletResponse resp, MemberService memberService) {
		
		this.req = req;
		this.resp = resp;
		//위에 private로 httpSession을 빼줘서 login에서도 쓸수 있게 해준다. 
		this.httpSession = req.getSession();
		
		int loginedMemberId = 0;
		Member loginedMember = null;
		
		if (httpSession.getAttribute("loginedMemberId") != null) {
			loginedMemberId = (int) httpSession.getAttribute("loginedMemberId");
			loginedMember = memberService.getMemberById(loginedMemberId);
		}
		
		this.loginedMemberId = loginedMemberId;
		this.loginedMember = loginedMember;
		//Rq 리모콘을 세팅
		this.req.setAttribute("rq", this);
	}

	public void jsPrintHistoryBack(String msg) {
		resp.setContentType("text/html; charset=UTF-8;");
		
		print(Util.jsHistoryBack(msg));
	}

	private void print(String str) {
		try {
			resp.getWriter().append(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
// member 전체를 받는다. 여러가지로 활용가능하다. 받는값은 member.getId여도 괜찮았다.
	public void login(Member member) {
		httpSession.setAttribute("loginedMemberId", member.getId());
	}

	public void logout() {
		httpSession.removeAttribute("loginedMemberId");
	}
	
	
	public String jsReturnOnView(String msg, boolean isHistoryBack) {

		req.setAttribute("msg", msg); //메시지를 담아서 넘긴다.
		req.setAttribute("isHistoryBack", isHistoryBack); // true 가넘어오면 historyback을 할것이다.

		return "usr/common/js";
	}
	
	public void initRq() {

	}
}
