package com.example.project.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.project.vo.Article;

@Mapper
public interface ArticleRepository {

	@Select("""
		SELECT A.*, M.nickname AS writerName 
			FROM article AS A
			INNER JOIN `member` AS M
			ON A.memberId = M.id
			WHERE MemberId = #{id}
			ORDER BY id DESC
			LIMIT #{limitStart}, #{itemsInAPage}
			""")
	public List<Article> getArticles(int id, int itemsInAPage, int limitStart);

	
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

	@Select("""
		SELECT A.*, M.userMessage,M.response
				FROM article AS A
				INNER JOIN `chat` AS M
				ON A.id = M.articleId
				WHERE A.id = #{id}	
			""")
	public Article[] getForPrintArticles(int id);

	@Select("""
			SELECT COUNT(*)
			FROM article
			WHERE memberId = #{memberId}
			""")
	public int getArticlesCnt(int memberId);

	@Delete("""
			DELETE FROM article
			WHERE id = #{id}
			""")
	public void deleteArticle(int id);

	@Select("""
			SELECT * 
			FROM article
			WHERE id = #{id}
			""")
	public Article getArticleById(int id);
	
	
}
