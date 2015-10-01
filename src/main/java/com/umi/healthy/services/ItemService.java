package com.umi.healthy.services;

import static com.umi.healthy.data.persist.OfyService.ofy;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import lombok.extern.java.Log;

import com.google.appengine.labs.repackaged.com.google.common.collect.Lists;
import com.umi.healthy.data.Category;
import com.umi.healthy.data.Item;
import com.umi.healthy.data.X_CategoryItem;
import com.umi.healthy.data.persist.DBService;
import com.umi.healthy.utils.StringUtil;

@Log
public class ItemService extends DBService{
	
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
	public List<Item> loadItemsByCategory(String cat_slug, Integer limit, Integer offset ){
		log.info("Start loadItemsByCategory: "+cat_slug);
		List<Item>  items = Lists.newArrayList() ;
		String ids = null;
		
		try{
		/*	List<String> ids = Lists.newArrayList();
			AppScreenManager screenManager = new AppScreenManager();
			for(Application app:appList)
			{
				ids.add(app.getCID());
			}*/
			List<X_CategoryItem> x_list =ofy().load().type(X_CategoryItem.class).filter("cat_slug",cat_slug).limit(limit).offset(offset).list();
			for(X_CategoryItem x:x_list){
				ids=ids+x.getItem_slug();
			}
			
			Map<String, Item> items_map = ofy().load().type(Item.class).ids(ids);
			for(Entry<String, Item> i:items_map.entrySet()){
				items.add(i.getValue());
			}
		}catch(Exception e ) {
			log.severe(StringUtil.exceptionFormat( e ));
		}
		
		log.info("End loadItemsByCategory");
		return items;
	}


	public void loadToDatastroge(List<String[]> content ) {
		log.info("Start uploadCsvtoDataStore");
		
		String[] line= null;
		List<Item> itemList =  Lists.newArrayList();
		String[] header = content.get(0);
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
		
		for (int rowIndex= 1; rowIndex < content.size(); rowIndex++) {
			line = content.get(rowIndex);
			Item item = new Item();
			List<String> recipeCategory = null;
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
							recipeCategory = Arrays.asList(array);
							item.setRecipeCategory(recipeCategory); 
							break;
							
						case "totalTime": item.setTotalTime(line[colIndex]); break;
						case "recipeYield": item.setRecipeYield(line[colIndex]); break;
						case "ingredients": item.setIngredients(line[colIndex]); break;
						case "nutrition": item.setNutrition(line[colIndex]); break;
						case "priority": item.setPriority(Integer.valueOf(line[colIndex])); break;
						case "dateCreated":
							if(header[colIndex] != null ){
								try {
									Date parsedDate = dateFormat.parse ( line[colIndex] );
									item.setDateCreated( parsedDate.getTime() );
								} catch (ParseException e) {
									log.severe("parsedDate:  "+ line[colIndex]+" "+ e.getMessage() );
									item.setDateCreated(System.currentTimeMillis());
								}
								
							}else{
								item.setDateCreated(System.currentTimeMillis());
							}
							
							 break;
							 
						case "datePublished":
							if(header[colIndex] != null ){
								try {
									Date parsedDate = dateFormat.parse ( line[colIndex] );
									item.setDatePublished( parsedDate.getTime() );
								} catch (ParseException e) {
									log.severe("parsedDate:  "+ line[colIndex]+" "+ e.getMessage() );
									item.setDateCreated(System.currentTimeMillis());
								}
								
							}else{
								item.setDateCreated(System.currentTimeMillis());
							}
							
							 break;
							 
						case "dateModified":
							if(header[colIndex] != null ){
								try {
									Date parsedDate = dateFormat.parse ( line[colIndex] );
									item.setDateModified( parsedDate.getTime() );
								} catch (ParseException e) {
									log.severe("parsedDate:  "+ line[colIndex]+" "+ e.getMessage() );
									item.setDateCreated(System.currentTimeMillis());
								}
								
							}else{
								item.setDateCreated(System.currentTimeMillis());
							}
							
							 break;
							 
						default:log.info("unregistered column "+header[colIndex]); break;
					}
					
				}// end of if
				
			}// end of loop by colindex
			
			if(!item.getName().isEmpty()){
				item.setActive(true);
				item = save(item);
				saveItemCategory(item, recipeCategory);
			}
		}// end of cycle
		
		log.info("end uploadCsv toDataStore");
	}
	public void saveItemCategory(Item item, List<String> slug_list){
		log.info("Start  saveItemCategory");
		if(item == null || slug_list.isEmpty() ) return;
		
		CategoryService categoryService = new CategoryService(); 
		
		
		for(String cat_slug:slug_list){
			
			log.info("X_CategoryItem:: cat_slug "+cat_slug);
		    Category category =  categoryService.loadCategory(cat_slug);
		    log.info(" category.getSlug() " + category.getSlug() );
		    
		    X_CategoryItem x = ofy().load().type(X_CategoryItem.class).filter("cat_slug",cat_slug).filter("item_slug", item.getSlug() ).first().now();
		   
		    if(category!=null && x == null){
		    	x = new X_CategoryItem();
		    	x.setCat_slug( cat_slug );
		    	x.setItem_slug( item.getSlug() );
		    	save(x);
		    	log.info(" x.getCat_slug(): "+x.getCat_slug() );
		    }
			
		}
		log.info("end  saveItemCategory");
	}
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

	public Item saveItem(String slug, String name, String thumbnailUrl,
			String about, String description, String categories,
			String totalTime, String recipeYield, String ingredients,
			String nutrition,  Boolean active,
			Long datePublished, Long dateCreated, Long dateModified) {
			List<String> recipeCategory =null;
			Item  item =  null;
			
			
			try{
				
				item =load( Item.class ,slug);
				
				if(item == null){
					item = new Item();
					item.setSlug( slug );
					item.setDateCreated( System.currentTimeMillis() );
					item.setDatePublished( System.currentTimeMillis() );
				}
				
				item.setAbout(about);
				item.setDescription(description);
				item.setName( name );
				item.setDateModified( System.currentTimeMillis() );
				item.setIngredients(ingredients);
				item.setNutrition(nutrition);
				
				if(!categories.isEmpty()){
					String[] array = categories.split(",");
					recipeCategory = Arrays.asList(array);
					item.setRecipeCategory(recipeCategory); 
				}
				
				item.setRecipeYield(recipeYield);
				item.setThumbnailUrl(thumbnailUrl );            
				item.setTotalTime( totalTime);
				item.setActive(true);
				item =  save(item);
				saveItemCategory(item, recipeCategory);
				
			}catch(Exception e ) {
				log.severe("newitem.getitem_name(): " + name);
				log.severe(StringUtil.exceptionFormat( e ));
			}
		
		return item;
	}

}
