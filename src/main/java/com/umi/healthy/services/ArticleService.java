package com.umi.healthy.services;

import static com.umi.healthy.data.persist.OfyService.ofy;

import java.util.List;

import lombok.extern.java.Log;

import com.umi.healthy.data.Article;
import com.umi.healthy.data.persist.DBService;
import com.umi.healthy.utils.StringUtil;
@Log
public class ArticleService extends DBService{
	public List<Article> loadArticles(Boolean active) {
		if(active){
		return ofy().load().type(Article.class).filter( "active ==",active).list();
		}else{
			return loadAll(Article.class);
		}
	}
	
	
	public Article loadArticle(String slug) {
		return load(Article.class,slug);
	}

	public Article saveArticle(Article newarticle) {
		Article  article =  null;
		
		try{
			
			article = load( Article.class , newarticle.getSlug() );
			
			if(article == null){
				article = new Article();
				article.setSlug( newarticle.getSlug() );
				article.setDateCreated( System.currentTimeMillis() );
				article.setDatePublished(System.currentTimeMillis() );
			}
			article.setDescription(newarticle.getDescription());
			article.setName(newarticle.getName());
			article.setDateModified(System.currentTimeMillis() );
			article.setPriority(newarticle.getPriority());
			article.setActive(newarticle.getActive());
			article = save(article);
			
		}catch(Exception e ) {
			log.severe("newarticle.getarticle_name(): " + newarticle.getName());
			log.severe(StringUtil.exceptionFormat( e ));
		}
		
		return article;
		
	}
}
