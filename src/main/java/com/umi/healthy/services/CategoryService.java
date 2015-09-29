package com.umi.healthy.services;

import java.util.List;

import lombok.extern.java.Log;

import com.google.appengine.labs.repackaged.com.google.common.collect.Lists;
import com.umi.healthy.data.Category;
import com.umi.healthy.data.persist.DBService;
import com.umi.healthy.utils.StringUtil;

@Log
public class CategoryService {
	
	DBService db = new DBService();
	
	public Category saveCategory( Category newcategory ){
		Category  category =  null;
		
		try{
			
			category = db.load( Category.class , newcategory.getSlug() );
			
			if(category == null){
				category = new Category();
				category.setSlug( newcategory.getSlug() );
				category.setDateCreated( System.currentTimeMillis() );
				category.setDatePublished(System.currentTimeMillis() );
			}
			
			category.setDescription(newcategory.getDescription());
			category.setName(newcategory.getName());
			category.setDateModified(System.currentTimeMillis() );
			category.setPriority(newcategory.getPriority());
			category.setActive(newcategory.getActive());
			category = db.save(category);
			
		}catch(Exception e ) {
			log.severe("newcategory.getcategory_name(): " + newcategory.getName());
			log.severe(StringUtil.exceptionFormat( e ));
		}
		
		return category;
	}
	
	public Category loadCategory( String slug ){

		Category  category = null;
		
		try{
			
			if(slug != null){
				category = db.load( Category.class , slug );
			}
		
		}catch(Exception e ) {
			log.severe(StringUtil.exceptionFormat( e ));
		}
		
		return category;
	}
	public List<Category> loadCategories(){

		List<Category>  categories = Lists.newArrayList() ;
		
		try{
			
			categories = db.load( Category.class,"active", true,"priority");
		
		}catch(Exception e ) {
			log.severe(StringUtil.exceptionFormat( e ));
		}
		
		return categories;
	}

	public void saveCategories(List<Category> categories) {
		try{
			
			db.save(categories);
			
		}catch(Exception e){
			log.severe(StringUtil.exceptionFormat( e ));
			for(Category category:categories){
				db.save(category);
			}
		}
		
	}

}
