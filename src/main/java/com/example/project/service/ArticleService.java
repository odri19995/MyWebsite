package com.example.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project.repository.ArticleRepository;
import com.example.project.vo.Article;
import com.example.project.vo.ResultData;

@Service
public class ArticleService {
	
	private ArticleRepository articleRepository;
	
	@Autowired
	public ArticleService(ArticleRepository articleRepository) {
		this.articleRepository = articleRepository;
	}
	
	public List<Article> getArticles(int id){
		return articleRepository.getArticles(id);
	}

	public void writeArticle(String title, int memberId) {
		articleRepository.writeArticle(title,memberId);
		
	}
	
	

	public int getLastInsertId() {
		return articleRepository.getLastInsertId();
	}

	public Article[] getForPrintArticles(int id) {
		
		Article[] articles = articleRepository.getForPrintArticles(id);
		
		return articles;
	}
	
	public ResultData actorCanMD(int loginedMemberId, Article article) {
		if(article == null) {
			return ResultData.from("F-1", "해당 게시물은 존재하지 않습니다");
		}
		
		if (loginedMemberId != article.getMemberId()) {
			return ResultData.from("F-B", "해당 게시물에 대한 권한이 없습니다");	
		}
		
		
		return ResultData.from("S-1", "가능");
	}
	
	

	}