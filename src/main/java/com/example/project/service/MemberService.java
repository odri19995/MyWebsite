package com.example.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project.repository.MemberRepository;
import com.example.project.util.Util;
import com.example.project.vo.Member;
import com.example.project.vo.ResultData;

@Service
public class MemberService {
	
	private MemberRepository memberRepository;
	
	@Autowired
	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}
	
	public ResultData<Integer> doJoin(String loginId, String loginPw, String name, String nickname, String email) {

		Member existsMember = getMemberByLoginId(loginId);
		
		if (existsMember != null) {
			return ResultData.from("F-7", Util.f("이미 사용중인 아이디(%s) 입니다", loginId));
		}
		
		existsMember = getMemberByNickname(nickname);
		
		if (existsMember != null) {
			return ResultData.from("F-8", Util.f("이미 사용중인 닉네임(%s) 입니다", nickname));
		}
		
		existsMember = getMemberByNameAndEmail(name, email);
		
		if (existsMember != null) {
			return ResultData.from("F-9", Util.f("이미 사용중인 이름(%s)과 이메일(%s) 입니다", name, email));
		}
		
		memberRepository.doJoin(loginId, loginPw, name, nickname, email);
		
		return ResultData.from("S-1", Util.f("%s회원님이 가입되었습니다", loginId),"id", memberRepository.getLastInsertId());
	}
	
	public ResultData<Integer> doJoinKakao(Long id, String nickname, String email) {
		
		System.out.println("서비스카카오 아이디 번호 : " +id);
		System.out.println("서비스카카오 닉네임 : " +nickname);
		System.out.println("서비스카카오 이메일 : " +email);
		String loginId = Long.toString(id);
		
		Member existsMember = getMemberByLoginId(loginId);
		
		if (existsMember != null) {
			return ResultData.from("F-7", Util.f("이미 사용중인 아이디(%s) 입니다", loginId));
		}
		
		memberRepository.doJoinKakao(loginId, nickname, email);
		return ResultData.from("S-1", Util.f("%s회원님이 카카오톡으로 가입되었습니다", nickname),"id", memberRepository.getLastInsertId());
	}
	
	private Member getMemberByNameAndEmail(String name, String email) {
		return memberRepository.getMemberByNameAndEmail(name, email);
	}

	private Member getMemberByNickname(String nickname) {
		return memberRepository.getMemberByNickname(nickname);
	}

	public Member getMemberByLoginId(String loginId) {
		return memberRepository.getMemberByLoginId(loginId);
	}

	public Member getMemberById(int id) {
		return memberRepository.getMemberById(id);
	}
	public void doModify(int loginedMemberId, String nickname, String email) {
		memberRepository.doModify(loginedMemberId, nickname, email);
	}

	public void doPasswordModify(int loginedMemberId, String loginPw) {
		memberRepository.doPasswordModify(loginedMemberId, loginPw);
	}
}
