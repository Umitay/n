package com.umi.common.action;

import java.io.IOException;
import java.net.URISyntaxException;

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
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import lombok.extern.java.Log;

import com.umi.common.data.User;
import com.umi.common.services.ItemService;
import com.umi.common.services.UserService;
import com.umi.common.utils.CustomException;

@Path("/newsletter")
@Log
@PermitAll
public class NewsletterServlet {

	@Context HttpServletRequest request;
	@Context HttpServletResponse response;
	
	ItemService itemService = new ItemService(); 
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public Response view( ) throws IOException, URISyntaxException {
		
		try {
			request.getRequestDispatcher("/common/newsletter.jsp").forward(request, response);
			
		} catch (ServletException | IOException e) {
			log.severe(e.getMessage());
			throw new CustomException(Status.NOT_FOUND, "Something went wrong.");
		}
		return Response.ok().build();
	}
	
	@Path("/subscribe")
	@POST
	@Consumes("application/x-www-form-urlencoded")
	public void save (	
			 @DefaultValue("") @FormParam("email") String email,
			 @DefaultValue("") @FormParam("description") String description
			 ) throws IOException {
		
		log.info("Start add with email: "+email);
	
		if(email.length() > 0 ){
			UserService us = new UserService();
			User user = us.loadUser(email);
			
			if(user == null){
				user= new User();
				user.setEmail(email);
			}
			
			user.setIs_subscribed(true);
			us.save(user);
			
		}
		
		log.info("End save ");
	}

}
