package com.example.project.repository;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
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

	
	
	
	@Select(""" 
			SELECT userMessage
				FROM `chat`
				WHERE MemberId = #{memberId}
				ORDER BY id DESC
				LIMIT 3;
		""")
	public List<String> loadUserInput(int memberId);
	
	
	@Select(""" 
			SELECT response
				FROM `chat`
				WHERE MemberId = #{memberId}
				ORDER BY id DESC
				LIMIT 3;
		""")
	public List<String> loadAIresponse(int memberId);
	
}