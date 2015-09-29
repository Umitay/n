package com.umi.healthy.action;

import java.io.IOException;

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

import lombok.extern.java.Log;

import com.umi.healthy.data.Item;
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
		
		Item item =  itemService.loadItem(slug); 
		
		if( item == null ){
			throw new CustomException(Status.NOT_FOUND, "Something went wrong.");
		}
		
		
		
		try {
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
			@DefaultValue("") @FormParam("name") String  name,
			@DefaultValue("") @FormParam("description") String  description,
			@DefaultValue("1000000") @FormParam("priority") Integer  priority ) throws IOException {
		
		log.info("Start save ");
		
		if(slug.length() <=0 ){
			throw new CustomException(Status.BAD_REQUEST, "Field 'slug' is missing.");
		}
		
		if(name.length() <=0 ){
			throw new CustomException(Status.BAD_REQUEST, "Field 'name' is missing.");
		}
		
		Item newItem =  itemService.loadItem(slug); 
		
		if(  newItem == null ){
			 newItem = new Item();
		}
		
		newItem.setDescription(description);
		newItem.setName(name);
		newItem.setSlug(slug);
		newItem.setPriority(priority);
		itemService.saveItem(newItem);
		response.sendRedirect("/recipe/e/"+slug);
		log.info("End save ");
	}

}
