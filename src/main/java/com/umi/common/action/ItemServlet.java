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

@Path("/recipe")
@Log
@PermitAll
public class ItemServlet extends BaseServlet {


	@GET
	@Produces(MediaType.TEXT_HTML)
	public Response index()throws IOException, ServletException{
		log.info("1");
		response.setStatus(Response.Status.BAD_REQUEST.getStatusCode());
		request.getRequestDispatcher("/404.jsp").forward(request, response);
		return Response.status(Response.Status.BAD_REQUEST.getStatusCode()).build();
	}
	
	@Path("/{slug}")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public Response view(@PathParam("slug") String slug ) throws IOException, URISyntaxException, ServletException {
		log.info("slug"+slug);
		Item item =  itemService.loadItem(slug); 
		
		if(request.getServerName().contains("appspot.com")){
			request.setAttribute("unvisible", true);
		}
		log.info("item"+item);
		
		if( item == null ){
			if(StringUtil.is_rus(slug) ){
				slug = StringUtil.generateSlug(slug);
				return Response.status(Response.Status.MOVED_PERMANENTLY).location(new URI("/recipe/"+slug)).build();
			}
			response.setStatus(Response.Status.NOT_FOUND.getStatusCode());
			request.getRequestDispatcher("/404.jsp").forward(request, response);
			return Response.status(Response.Status.NOT_FOUND.getStatusCode()).build();
		}
		
		try {
			List<Category> all_categories =  categoryService.loadAllCategories(); 
			List<Category> item_categories =  Lists.newArrayList();
			
			for (Category cat : all_categories) {
				if(item.getCategories().contains( cat.getSlug() )){
					item_categories.add(cat);
				}
			}
			
			Date d = new Date( item.getDatePublished() );
			request.setAttribute("item_datePublished", DateFormatUtils.format(d,"yyyy-MM-dd"));
			
			Date dm = new Date( item.getDateModified() );
			request.setAttribute("item_dateModified", DateFormatUtils.format(dm,"yyyy-MM-dd"));
			request.setAttribute("categories", categories);
			request.setAttribute("item_categories", item_categories);
			request.setAttribute("category", item_categories.get(0));
			request.setAttribute("item", item);
			request.setAttribute("articles", articles);
			request.setAttribute("items", items);
			request.setAttribute("meta_title", item.getMeta_title());
			request.setAttribute("meta_keywords", item.getMeta_keywords());
			request.setAttribute("meta_description", item.getMeta_description());
			
			request.getRequestDispatcher("/item/item.jsp").forward(request, response);
			
		} catch (ServletException | IOException e) {
			log.severe(e.getMessage());
			throw new CustomException(Status.NOT_FOUND, "Something went wrong.");
		}
		return Response.ok().build();
	}
}
