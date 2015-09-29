package com.umi.healthy.services;

import static com.umi.healthy.data.persist.OfyService.ofy;

import java.util.List;

import lombok.extern.java.Log;

import com.google.appengine.labs.repackaged.com.google.common.collect.Lists;
import com.umi.healthy.data.Item;
import com.umi.healthy.data.persist.DBService;
import com.umi.healthy.utils.StringUtil;

@Log
public class ItemService extends DBService{
	
	
	public Item saveItem( Item newItem ){
		Item  item =  null;
		
		try{
			
			item =load( Item.class , newItem.getSlug() );
			
			if(item == null){
				item = new Item();
				item.setSlug( newItem.getSlug() );
				item.setDateCreated( System.currentTimeMillis() );
				item.setDatePublished( System.currentTimeMillis() );
			}
			item.setAbout(newItem.getAbout());
			item.setDescription(newItem.getDescription());
			item.setName( newItem.getName() );
			item.setPriority( newItem.getPriority() );
			item.setDateModified( System.currentTimeMillis() );
			item.setIngredients(newItem.getDescription());
			item.setNutrition(newItem.getNutrition());
			item.setRecipeCategory(newItem.getRecipeCategory());
			item.setRecipeYield(newItem.getRecipeYield());
			item.setThumbnailUrl(newItem.getThumbnailUrl() );            
			item.setTotalTime( newItem.getTotalTime() );
			item.setActive(newItem.getActive());
			item =  save(item);
			
		}catch(Exception e ) {
			log.severe("newitem.getitem_name(): " + newItem.getName());
			log.severe(StringUtil.exceptionFormat( e ));
		}
		
		return item;
	}
	
	public Item loadItem( String slug ){

		Item  item = null;
		
		try{
			
			if(slug != null){
				item = load( Item.class , slug );
			}
		
		}catch(Exception e ) {
			log.severe(StringUtil.exceptionFormat( e ));
		}
		
		return item;
	}
	public List<Item> loadItems(Integer limit){

		List<Item>  items = Lists.newArrayList() ;
		
		try{
		 items = ofy().load().type(Item.class).filter("active", true).order("-datePublished").limit(limit).list();
		
		}catch(Exception e ) {
			log.severe(StringUtil.exceptionFormat( e ));
		}
		
		return items;
	}
	public List<Item> loadItemsByCategory(String cat_slug){

		List<Item>  items = Lists.newArrayList() ;
		
		try{
			
		  items = load( Item.class,"active", true,"priority");
		
		}catch(Exception e ) {
			log.severe(StringUtil.exceptionFormat( e ));
		}
		
		return items;
	}
	public void saveItems(List<Item> items) {
		try{
			
			save(items);
			
		}catch(Exception e){
			log.severe(StringUtil.exceptionFormat( e ));
			for(Item item:items){
				save(item);
			}
		}
		
	}

}
