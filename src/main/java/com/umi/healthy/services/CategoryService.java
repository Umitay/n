package com.umi.healthy.services;

import static com.umi.healthy.data.persist.OfyService.ofy;

import java.util.List;

import lombok.extern.java.Log;

import com.google.appengine.labs.repackaged.com.google.common.collect.Lists;
import com.umi.healthy.data.Category;
import com.umi.healthy.data.SitemapIndex;
import com.umi.healthy.data.persist.DBService;
import com.umi.healthy.utils.StringUtil;

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
