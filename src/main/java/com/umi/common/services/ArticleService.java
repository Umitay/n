package com.umi.common.services;

import static com.umi.common.data.persist.OfyService.ofy;

import java.util.List;

import lombok.extern.java.Log;

import com.umi.common.data.Article;
import com.umi.common.data.SitemapIndex;
import com.umi.common.data.persist.DBService;
import com.umi.common.utils.StringUtil;
@Log
public class ArticleService extends DBService{
	public List<Article> loadArticles(Boolean active, Integer limit, Integer offset ) {
		if(active){
			return ofy().load().type(Article.class).filter( "active ==",active).limit(limit).offset(offset).order("-dateCreated").list();
		}else{
			return ofy().load().type(Article.class).limit(limit).offset(offset).order("-dateCreated").list();
		}
	}
	public List<Article> loadArticles(Boolean active) {
	return  loadArticles(active,10000000, 0 );
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
			article.setAbout(newarticle.getAbout());
			article.setThumbnailUrl(newarticle.getThumbnailUrl());
			article.setAlt( newarticle.getAlt() );
			article.setLink_title(newarticle.getLink_title());
			article.setMeta_title(newarticle.getMeta_title());
			article.setMeta_keywords(newarticle.getMeta_keywords());
			article.setMeta_description(newarticle.getMeta_description());
			article.setAds_horizont1(newarticle.getAds_horizont1());
			article.setAds_horizont2(newarticle.getAds_horizont2());
			article.setAds_side1(newarticle.getAds_side1());
			article.setAds_side2(newarticle.getAds_side2());
			
			article = save(article);
			
			SitemapIndex sitemap = load(SitemapIndex.class, "1");
			
			if(sitemap == null){
				sitemap = new SitemapIndex();
				sitemap.setId("1");
			}
			
			sitemap.setArticle_date_modified (System.currentTimeMillis());
			save(sitemap);
		}catch(Exception e ) {
			log.severe("newarticle.getarticle_name(): " + newarticle.getName());
			log.severe(StringUtil.exceptionFormat( e ));
		}
		
		return article;
		
	}
}
