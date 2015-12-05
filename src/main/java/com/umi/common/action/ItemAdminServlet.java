package com.umi.common.action;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import lombok.extern.java.Log;

import com.google.appengine.repackaged.com.google.api.client.util.Lists;
import com.umi.common.data.Article;
import com.umi.common.data.Category;
import com.umi.common.data.Item;
import com.umi.common.data.X_CategoryItem;
import com.umi.common.services.ArticleService;
import com.umi.common.services.CategoryService;
import com.umi.common.services.ItemService;
import com.umi.common.utils.CustomException;
import com.umi.common.utils.StringUtil;
import com.umi.common.data.persist.EnvironmentConfig;

@Path("/n/item")
@Log
@PermitAll
public class ItemAdminServlet {

	@Context HttpServletRequest request;
	@Context HttpServletResponse response;
	
	ItemService itemService = new ItemService(); 

	@Path("/e/{slug}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@RolesAllowed({"ADMIN", "API"})
	public void edit( @DefaultValue("") @PathParam("slug") String slug ) {
		
		String item_categories = "";
		Item item =  itemService.loadItem(slug); 
		if(item !=null){
			item_categories = StringUtils.join( item.getCategories(), ",");
		}
		
		CategoryService categoryService = new CategoryService(); 
		List<Category> all_categories =  categoryService.loadAllCategories(); 
		
		try {
			request.setAttribute("categories", all_categories);
			request.setAttribute("item_categories", item_categories );
			request.setAttribute("item", item);
			if(request.getServerName().contains("appspot.com")){
				request.setAttribute("is_admin", true);
			}
			request.getRequestDispatcher("/n/item_form.jsp").forward(request, response);
			
		} catch (ServletException | IOException e) {
			log.severe(e.getMessage());
			throw new CustomException(Status.NOT_FOUND, "Something went wrong.");
		}
	}
	
	@Path("/save")
	@POST
	@Consumes("application/x-www-form-urlencoded")
	@RolesAllowed({"ADMIN", "API", "SEO"})
	public void save (	
			 @DefaultValue("") @FormParam("slug") String  slug,
			 @DefaultValue("") @FormParam("name") String name,
			 @DefaultValue("") @FormParam("alt") String alt,
			 @DefaultValue("") @FormParam("thumbnailUrl") String thumbnailUrl,
			 @DefaultValue("") @FormParam("thumbnailUrl2") String thumbnailUrl2,
			 @DefaultValue("") @FormParam("about") String about,
			 @DefaultValue("") @FormParam("description") String description,
			 @DefaultValue("") @FormParam("categories") String categories,
			 @DefaultValue("") @FormParam("active") Boolean active,
			 @DefaultValue("") @FormParam("datePublished") Long  datePublished,
			 @DefaultValue("") @FormParam("dateCreated") Long dateCreated,
			 @DefaultValue("") @FormParam("dateModified") Long dateModified,
			 @DefaultValue("") @FormParam("fb_share")	String fb_share ,
			 @DefaultValue("") @FormParam("vk_share") String vk_share ,
			 @DefaultValue("") @FormParam("lj_share") String lj_share ,
			 @DefaultValue("") @FormParam("twitter_share") String twitter_share,
			 @DefaultValue("") @FormParam("link_title") String link_title, 
			 @DefaultValue("") @FormParam("meta_title") String meta_title,
			 @DefaultValue("") @FormParam("meta_keywords") String meta_keywords, 
			 @DefaultValue("") @FormParam("meta_description") String meta_description,
			 @DefaultValue("") @FormParam("ads_horizont1") String ads_horizont1, 
			 @DefaultValue("") @FormParam("ads_horizont2") String ads_horizont2,
			 @DefaultValue("") @FormParam("ads_side1") String ads_side1, 
			 @DefaultValue("") @FormParam("ads_side2") String ads_side2,
			 @DefaultValue("") @FormParam("totalTime") String totalTime,
			 @DefaultValue("") @FormParam("recipeYield") String recipeYield,
			 @DefaultValue("") @FormParam("ingredients") String ingredients,
			 @DefaultValue("") @FormParam("nutrition") String  nutrition
			 ) throws IOException {
		
		log.info("Start save with slug: "+slug);
	
		if(name.length() <=0 ){
			response.sendRedirect("/n");
			throw new CustomException(Status.BAD_REQUEST, "Field 'name' is missing.");
		}
		
		if(slug.length() >0 ){
			
			Item item = itemService.loadItem(slug);
			
			if( item != null ) {
				log.info("Found the item with slug: " + slug);
				
				if(!item.getName().equals(name) ){
					log.info(" Found an Item by given slug, but name of the Item was changed, therefor will be deleted and than will created with new generated slug.");
					List<X_CategoryItem> x  = itemService.load(X_CategoryItem.class, "item_slug", item.getSlug() );
					if(!x.isEmpty() ){
						log.info("X_CategoryItem size: " + x.size() );
						itemService.deleteList(x);
					}
					log.info("start delete the item with slug: " + slug);
					itemService.delete(item);
			}
			}
		}
		slug = StringUtil.generateSlug(name);
		
		Boolean is_admin = false;
		if(request.getServerName().contains("appspot.com")){
			is_admin = true;
		}
		
		itemService.saveItem(slug,name,alt,thumbnailUrl,thumbnailUrl2,about,description,
					categories,totalTime,recipeYield,ingredients,nutrition,
					active,datePublished,dateCreated,dateModified,
					fb_share, vk_share, lj_share, twitter_share,is_admin, link_title,
					meta_title, meta_keywords, meta_description,
					ads_horizont1,ads_horizont2, ads_side1, ads_side2);
		  
		response.sendRedirect("/n");
		log.info("End save ");
	}
	@Path("/update")
	@GET
	@RolesAllowed({"ADMIN", "API"})
	public void edit( ) {
		
		List<Item> items =  itemService.loadAll(Item.class);
		
		for(Item item:items){
			item.setMeta_title(EnvironmentConfig.getInstance().getMeta_link_title()+ item.getName());
			item.setAlt(EnvironmentConfig.getInstance().getMeta_icon() + item.getName() );
			item.setMeta_title(item.getName()+EnvironmentConfig.getInstance().getMeta_title());
			item.setMeta_keywords(EnvironmentConfig.getInstance().getMeta_keywords()+ item.getName() );
			item.setMeta_description(item.getAbout());
		}
		itemService.save(items);
	
		
	}
	
}
