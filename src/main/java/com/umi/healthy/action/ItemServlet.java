package com.umi.healthy.action;

import java.io.IOException;
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
import javax.ws.rs.core.Response.Status;

import org.apache.commons.lang3.time.DateFormatUtils;

import lombok.extern.java.Log;

import com.google.appengine.repackaged.com.google.api.client.util.Lists;
import com.umi.healthy.data.Category;
import com.umi.healthy.data.Item;
import com.umi.healthy.services.CategoryService;
import com.umi.healthy.services.ItemService;
import com.umi.healthy.utils.CustomException;

@Path("/recipe")
@Log
@PermitAll
public class ItemServlet {

	@Context HttpServletRequest request;
	@Context HttpServletResponse response;
	
	ItemService itemService = new ItemService(); 
	
	@Path("/{slug}")
	@GET
	public void view( @DefaultValue("") @PathParam("slug") String slug ) {
		
		if(slug.length() <=0 ){
			throw new CustomException(Status.BAD_REQUEST, "Field 'slug' is missing.");
		}
		if(request.getServerName().contains("appspot.com")){
			request.setAttribute("unvisible", true);
		}
		Item item =  itemService.loadItem(slug); 
		
		if( item == null ){
			throw new CustomException(Status.NOT_FOUND, "Something went wrong.");
		}
		
		
		
		CategoryService categoryService = new CategoryService(); 
		List<Category> categories =  categoryService.loadTopCategories(); 
		List<Category> all_categories =  categoryService.loadAllCategories(); 
		List<Category> item_categories =  Lists.newArrayList();
		
		for (Category cat : all_categories) {
			if(item.getRecipeCategory().contains( cat.getSlug() )){
				item_categories.add(cat);
			}
		}
		
		try {
			Date d = new Date( item.getDatePublished() );
			request.setAttribute("item_datePublished", DateFormatUtils.format(d,"dd.MM.yyyy"));
			
			request.setAttribute("categories", categories);
			request.setAttribute("item_categories", item_categories);
			
			request.setAttribute("item", item);
			request.getRequestDispatcher("/item.jsp").forward(request, response);
			
		} catch (ServletException | IOException e) {
			log.severe(e.getMessage());
			throw new CustomException(Status.NOT_FOUND, "Something went wrong.");
		}
	}

	@Path("/e/{slug}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@RolesAllowed({"ADMIN", "API"})
	public void edit( @DefaultValue("") @PathParam("slug") String slug ) {

		Item item =  itemService.loadItem(slug); 
		try {
			request.setAttribute("item", item);
			request.getRequestDispatcher("/item_form.jsp").forward(request, response);
			
		} catch (ServletException | IOException e) {
			log.severe(e.getMessage());
			throw new CustomException(Status.NOT_FOUND, "Something went wrong.");
		}
	}
	
	@Path("/save")
	@POST
	@Consumes("application/x-www-form-urlencoded")
	@RolesAllowed({"ADMIN", "API"})
	public void save (	
			 @DefaultValue("") @FormParam("slug") String  slug,
			 @DefaultValue("") @FormParam("name") String name,
			 @DefaultValue("") @FormParam("thumbnailUrl") String thumbnailUrl,
			 @DefaultValue("") @FormParam("about") String about,
			 @DefaultValue("") @FormParam("description") String description,
			 @DefaultValue("") @FormParam("recipeCategory") String recipeCategory,
			 @DefaultValue("") @FormParam("totalTime") String totalTime,
			 @DefaultValue("") @FormParam("recipeYield") String recipeYield,
			 @DefaultValue("") @FormParam("ingredients") String ingredients,
			 @DefaultValue("") @FormParam("nutrition") String  nutrition,
			 @DefaultValue("") @FormParam("active") Boolean active,
			 @DefaultValue("") @FormParam("datePublished") Long  datePublished,
			 @DefaultValue("") @FormParam("dateCreated") Long dateCreated,
			 @DefaultValue("") @FormParam("dateModified") Long dateModified
			 ) throws IOException {
		
		log.info("Start save ");
		
		if(slug.length() <=0 ){
			throw new CustomException(Status.BAD_REQUEST, "Field 'slug' is missing.");
		}
		
		if(name.length() <=0 ){
			throw new CustomException(Status.BAD_REQUEST, "Field 'name' is missing.");
		}
		
		
	
		itemService.saveItem(slug,name,thumbnailUrl,about,description,recipeCategory,totalTime,recipeYield,ingredients,nutrition,active,datePublished,dateCreated,dateModified);
		response.sendRedirect("/recipe/e/"+slug);
		log.info("End save ");
	}

}
