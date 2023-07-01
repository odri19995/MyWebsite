package com.example.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.project.service.MemberService;
import com.example.project.util.Util;
import com.example.project.vo.Member;
import com.example.project.vo.ResultData;
import com.example.project.vo.Rq;


@Controller
public class MemberController {
	
	private MemberService memberService;
	private Rq rq;
	
	@Autowired
	public MemberController(MemberService memberService, Rq rq) {
		this.memberService = memberService;
		this.rq = rq;
	}
	
	// 액션 메서드
	@RequestMapping("/usr/member/doJoin")
	@ResponseBody
	public String doJoin(String loginId, String loginPw, String name, String nickname, String email) {
		
		if (Util.empty(loginId)) {
			return Util.jsHistoryBack("아이디를 입력해주세요");
		}
		if (Util.empty(loginPw)) {
			return Util.jsHistoryBack("비밀번호를 입력해주세요");
		}
		if (Util.empty(name)) {
			return Util.jsHistoryBack("이름을 입력해주세요");
		}
		if (Util.empty(nickname)) {
			return Util.jsHistoryBack("닉네임을 입력해주세요");
		}
		if (Util.empty(email)) {
			return Util.jsHistoryBack("이메일을 입력해주세요");
		}
		
		ResultData<Integer> doJoinRd = memberService.doJoin(loginId, loginPw, name, nickname, email);
		
		if (doJoinRd.isFail()) {
			return Util.jsHistoryBack(doJoinRd.getMsg());
		}
		
		return Util.jsReplace(doJoinRd.getMsg(), "/");
	}
	
	
	@Controller // ctrl+shift+o 자동 임포트 
	public class RegisterController {
//		@RequestMapping(value="/register/add", method=RequestMethod.GET) // 신규회원 가입
		@GetMapping("/usr/member/join") // 4.3부터 추가
		public String register() {
			return "usr/member/registerForm";  // WEB-INF/views/registerForm.jsp
		}
	}
	
	

}