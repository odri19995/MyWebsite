package com.example.project.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.project.vo.Member;

@Mapper
public interface MemberRepository {


	@Insert("""
				INSERT INTO `member`
					SET regDate = NOW(),
						updateDate = NOW(),
						loginId = #{loginId},
						loginPw = #{loginPw},
						`name` = #{name},
						nickname = #{nickname},
						email = #{email}
			""")
	public int doJoin(String loginId, String loginPw, String name, String nickname, String email);
	
	@Insert("""
			INSERT INTO `member`
				SET regDate = NOW(),
					updateDate = NOW(),
					loginId = #{loginId},
					loginPw = #{loginId},
					`name` = #{nickname},
					nickname = #{nickname},
					email = #{email},
					kakaoStatus = 1
		""")
	public void doJoinKakao(String loginId, String nickname, String email);
	
	
	
	@Select(""" 
				SELECT *
					FROM `member`
					WHERE id = #{id}
			""")
	public Member getMemberById(int id);
	
	@Select("""
				SELECT LAST_INSERT_ID()		
			""")
	public int getLastInsertId();
	
	
	
	@Select(""" 
			SELECT *
				FROM `member`
				WHERE loginId = #{loginId}
		""")
	public Member getMemberByLoginId(String loginId);

	@Select("""
			SELECT *
				FROM `member`
				WHERE nickname = #{nickname}
			""")
	public Member getMemberByNickname(String nickname);

	@Select("""
			SELECT *
				FROM `member`
				WHERE `name` = #{name}
				AND email = #{email}
			""")
	public Member getMemberByNameAndEmail(String name, String email);
	
	
	@Update("""
			UPDATE `member`
				SET updateDate = NOW(),
					nickname = #{nickname},
					email = #{email}
				WHERE id = #{loginedMemberId}
			""")
	public void doModify(int loginedMemberId, String nickname,String email);

	
	
	
	
	
	
	


}

