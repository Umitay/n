package com.umi.healthy.services;

import java.util.List;

import com.umi.healthy.data.Article;
import com.umi.healthy.data.persist.DBService;

public class ArticleService extends DBService{
	public List<Article> loadArticles() {
		return loadAll(Article.class,"priority");
	}
	
	public Article loadArticle(String slug) {
		return load(Article.class,slug);
	}
}
