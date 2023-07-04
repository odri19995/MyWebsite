package com.example.project.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface OpenAIRepository {

	@Insert("""
			INSERT INTO `article`
				SET regDate = NOW(),
					memberId = #{id},
					userMessage = #{userInput},
					response = #{response},
					`body` = #{body};
		""")
	public void setUserInputResponse(int id, String userInput, String response, String body);

	
	
	
}