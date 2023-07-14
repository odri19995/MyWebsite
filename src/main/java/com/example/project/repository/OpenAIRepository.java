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
					articleId = 0,
					userMessage = #{userInput},
					response = #{response}
		""")
	public void setUserInputResponse(int id, String userInput, String response);

	
	
	
	@Select(""" 
			SELECT userMessage
				FROM `chat`
				ORDER BY id DESC
				LIMIT 3;
		""")
	public List<String> loadUserInput();
	
	
	@Select(""" 
			SELECT response
				FROM `chat`
				ORDER BY id DESC
				LIMIT 3;
		""")
	public List<String> loadAIresponse();



	@Select(""" 
			SELECT response
				FROM `chat`
				ORDER BY id DESC
				LIMIT 1;
		""")
	public int loadTrunId();

	
	
	
}