package com.umi.common.action;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import lombok.extern.java.Log;

import com.umi.common.data.Category;
import com.umi.common.services.CategoryService;
import com.umi.common.services.ItemService;
import com.umi.common.utils.Csv;
import com.umi.common.utils.CustomException;

@Path("/csv")
@Log
@PermitAll
public class CSVServlet  {
	@Context HttpServletRequest request;
	@Context HttpServletResponse response;
	
	@GET
	@RolesAllowed({"ADMIN", "API"})
	public void index( @DefaultValue("") @QueryParam("slug") String slug ) {
		try {
			CategoryService categoryService = new CategoryService(); 
			List<Category> categories =  categoryService.loadAllCategories(); 
			request.setAttribute("categories", categories);
			request.getRequestDispatcher("/n/csv_loader.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			throw new CustomException(Status.INTERNAL_SERVER_ERROR,  e.getMessage() );
		}
	}
	
	@Path("/upload")
	@POST
	@Consumes("application/x-www-form-urlencoded")
	@RolesAllowed({"ADMIN", "API"})
	public void upload ( @DefaultValue("") @FormParam("filename") String  filename) throws Exception {
		
		if(filename.length() <=0 ){
			throw new CustomException(Status.BAD_REQUEST, "Field 'filename' is missing.");
		}
		
		ItemService itemService = new ItemService();
		Csv csvManager = new Csv();
		
		csvManager.setUrl(filename);
		List<String[]> content = null;
		
		try {
			content = csvManager.fetchContent();
		} catch (Exception e) {
			throw new CustomException(Status.INTERNAL_SERVER_ERROR,  e.getMessage() );
		}
		
		itemService.loadToDatastroge( content );
		
		response.sendRedirect("/n");
	}

}
