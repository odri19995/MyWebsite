package com.example.project.repository;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.project.vo.Article;

@Mapper
public interface ArticleRepository {

	@Select("""
		SELECT A.*, M.name AS writerName 
			FROM article AS A
			INNER JOIN `member` AS M
			ON A.memberId = M.id
			ORDER BY id DESC
			""")
	public List<Article> getArticles();

	
	@Select("""
			SELECT LAST_INSERT_ID()		
		""")
	public int getLastInsertId();

	@Insert("""
			INSERT INTO `article`
				SET regDate = NOW(),
					title = #{title},
					memberId = #{memberId}
		""")
	public void writeArticle(String title, int memberId);
	
	
}
