package com.umi.common.services;

import static com.umi.common.data.persist.OfyService.ofy;

import java.util.List;

import lombok.extern.java.Log;

import com.google.appengine.labs.repackaged.com.google.common.collect.Lists;
import com.umi.common.data.Category;
import com.umi.common.data.SitemapIndex;
import com.umi.common.data.persist.DBService;
import com.umi.common.utils.StringUtil;

@Log
public class CategoryService  extends DBService{
	
	public List<Category> loadAllCategories() {
		return loadAll(Category.class,"priority");
	}
	public List<Category> loadTopCategories() {
		
		return ofy().load().type(Category.class).filter( "parent ==","").order("priority").list();
	}
	
	public Category loadCategory(String slug) {
		return load(Category.class,slug);
	}
	
	
	public Category saveCategory( Category newcategory ){
		Category  category =  null;
		
		try{
			
			category = load( Category.class , newcategory.getSlug() );
			
			if(category == null){
				category = new Category();
				category.setSlug( newcategory.getSlug() );
				category.setDateCreated( System.currentTimeMillis() );
				category.setDatePublished(System.currentTimeMillis() );
			}
			category.setParent( newcategory.getParent() );
			category.setDescription(newcategory.getDescription());
			category.setName(newcategory.getName());
			category.setDateModified(System.currentTimeMillis() );
			category.setPriority(newcategory.getPriority());
			category.setActive(newcategory.getActive());
			category.setLink_title(newcategory.getLink_title());
			category.setMeta_title(newcategory.getMeta_title());
			category.setMeta_keywords(newcategory.getMeta_keywords());
			category.setMeta_description(newcategory.getMeta_description());
			category.setAds_jumbotron(newcategory.getAds_jumbotron());
			category.setAds_horizont1(newcategory.getAds_horizont1());
			category.setAds_horizont2(newcategory.getAds_horizont2());
			
			category = save(category);
			
			SitemapIndex sitemap = load(SitemapIndex.class, "1");
			
			if(sitemap == null){
				sitemap = new SitemapIndex();
				sitemap.setActive(true);
				sitemap.setId("1");
			}
			
			sitemap.setCategory_date_modified(System.currentTimeMillis());
			save(sitemap);
			
		}catch(Exception e ) {
			log.severe("newcategory.getcategory_name(): " + newcategory.getName());
			log.severe(StringUtil.exceptionFormat( e ));
		}
		
		return category;
	}

}
