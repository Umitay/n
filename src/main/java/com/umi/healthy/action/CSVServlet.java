package com.umi.healthy.action;
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

import com.umi.healthy.data.Category;
import com.umi.healthy.services.ItemService;
import com.umi.healthy.utils.Csv;
import com.umi.healthy.utils.CustomException;

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
			request.getRequestDispatcher("/csv_loader.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			throw new CustomException(Status.INTERNAL_SERVER_ERROR,  e.getMessage() );
		}
	}
	
	@Path("/upload")
	@POST
	@Consumes("application/x-www-form-urlencoded")
	@RolesAllowed({"ADMIN", "API"})
	public String upload ( @DefaultValue("") @FormParam("filename") String  filename) {
		
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
		
		itemService.loadToDatastroge( content , filename );
		
		return "Data from the file: "+filename+" was loaded to data store";
	}

}
