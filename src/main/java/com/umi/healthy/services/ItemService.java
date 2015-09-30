package com.umi.healthy.services;

import static com.umi.healthy.data.persist.OfyService.ofy;

import java.util.Arrays;
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
		 items = ofy().load().type(Item.class).filter("active", true).order("-dateCreated").limit(limit).list();
		
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

	public void loadToDatastroge(List<String[]> content, String filename) {
		log.info("Start uploadCsvtoDataStore");
		
		String[] line= null;
		List<Item> itemList =  Lists.newArrayList();
		String[] header = content.get(0);
		
		for (int rowIndex= 1; rowIndex < content.size(); rowIndex++) {
			line = content.get(rowIndex);
			Item item = new Item();
			
			for(int colIndex = 0; colIndex < line.length; colIndex++){
				
				if(line[colIndex] != null && header[colIndex] !=null){
					
					switch (header[colIndex]) {
						case "name": item.setName(line[colIndex]); break;
						case "slug": item.setSlug(line[colIndex]); break;
						case "thumbnailUrl": item.setThumbnailUrl(line[colIndex]); break;
						case "about": item.setAbout(line[colIndex]); break;
						case "description": item.setDescription(line[colIndex]); break;
						case "recipeCategory": 
							String[] array = line[colIndex].split(",");
							List<String> recipeCategory = Arrays.asList(array);
							item.setRecipeCategory(recipeCategory); 
							break;
						case "totalTime": item.setTotalTime(line[colIndex]); break;
						case "recipeYield": item.setRecipeYield(line[colIndex]); break;
						case "ingredients": item.setIngredients(line[colIndex]); break;
						case "nutrition": item.setNutrition(line[colIndex]); break;
						case "priority": item.setPriority(Integer.valueOf(line[colIndex])); break;
						case "dateCreated": item.setDateCreated(System.currentTimeMillis()); break;
						default:log.info("unregistered column "+header[colIndex]); break;
					}
					
				}// end of if
				
			}// end of loop by colindex
			
			if(!item.getName().isEmpty()){
				 item.setActive(true);
				itemList.add(item);
			}
		}// end of cycle
		
		
		if(!itemList.isEmpty()){
			 save(itemList);
		}
		 log.info("end uploadCsv toDataStore");
	}

}
