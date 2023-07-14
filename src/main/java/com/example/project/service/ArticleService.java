package com.example.project.service;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project.repository.ArticleRepository;
import com.example.project.vo.Article;

@Service
public class ArticleService {
	
	private ArticleRepository articleRepository;
	
	@Autowired
	public ArticleService(ArticleRepository articleRepository) {
		this.articleRepository = articleRepository;
	}
	
	public List<Article> getArticles(){
		return articleRepository.getArticles();
	}

	public void writeArticle(String title, int memberId) {
		articleRepository.writeArticle(title,memberId);
		
	}
	
	

	public int getLastInsertId() {
		return articleRepository.getLastInsertId();
	}
	
	

	}