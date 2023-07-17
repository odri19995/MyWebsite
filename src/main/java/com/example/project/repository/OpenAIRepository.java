package com.example.project.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface OpenAIRepository {

	@Insert("""
			INSERT INTO `chat`
				SET regDate = NOW(),
					memberId = #{id},
					articleId = #{articleId},
					userMessage = #{userInput},
					response = #{response}
		""")
	public void setUserInputResponse(int id,int articleId, String userInput, String response);


	
}