package com.example.project.repository;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
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

	
	
	
	@Select(""" 
			SELECT body
				FROM `article`
				ORDER BY id DESC
				LIMIT 3;
		""")
	public List<String> loadUserInputResponse();

	
	
	
}