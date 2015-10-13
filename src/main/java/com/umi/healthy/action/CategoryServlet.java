package com.umi.healthy.action;

import java.io.IOException;
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

import lombok.extern.java.Log;

import com.google.appengine.repackaged.com.google.api.client.util.Lists;
import com.umi.healthy.data.Article;
import com.umi.healthy.data.Category;
import com.umi.healthy.data.Item;
import com.umi.healthy.services.ArticleService;
import com.umi.healthy.services.CategoryService;
import com.umi.healthy.services.ItemService;
import com.umi.healthy.utils.CustomException;

@Path("/category")
@Log
@PermitAll
public class CategoryServlet {

	@Context HttpServletRequest request;
	@Context HttpServletResponse response;
	
	CategoryService categoryService = new CategoryService(); 
	
	@Path("/{slug}")
	@GET
	public void view( @DefaultValue("") @PathParam("slug") String slug ) {
		log.info("Start view");
		if(slug.length() <=0 ){
			throw new CustomException(Status.BAD_REQUEST, "Field 'slug' is missing.");
		}
		if(request.getServerName().contains("appspot.com")){
			request.setAttribute("unvisible", true);
		}
		
		Category category =  categoryService.loadCategory(slug); 
		if( category == null ){
			throw new CustomException(Status.NOT_FOUND, "Something went wrong.");
		}
		
		List<Category> categories =  categoryService.loadTopCategories();
		
		ItemService itemService = new ItemService(); 
		List<Item>  items = itemService.loadItemsByCategory(slug,20,0,true);
		 
		ArticleService articleService = new ArticleService(); 
		List<Article> articles =  articleService.loadArticles(true);
		
		try {
			request.setAttribute("articles", articles);
			request.setAttribute("category", category);
			request.setAttribute("categories", categories);
			request.setAttribute("items", items);
			request.getRequestDispatcher("/category.jsp").forward(request, response);
			
		} catch (ServletException | IOException e) {
			log.severe(e.getMessage());
			throw new CustomException(Status.NOT_FOUND, "Something went wrong.");
		}
		log.info("End view");
	}

	@Path("/l/{slug}")
	@GET
	public void n_view( @DefaultValue("") @PathParam("slug") String slug ) {
		log.info("Start view");
		if(slug.length() <=0 ){
			throw new CustomException(Status.BAD_REQUEST, "Field 'slug' is missing.");
		}
		
		CategoryService categoryService = new CategoryService(); 
		Category category =  categoryService.loadCategory(slug); 
		
		
		if( category == null ){
			throw new CustomException(Status.NOT_FOUND, "Something went wrong.");
		}
		
		List<Category> categories =  categoryService.loadTopCategories();
		
		ItemService itemService = new ItemService(); 
		List<Item>  items = itemService.loadItemsByCategory(slug,20,0,false);
		
		ArticleService articleService = new ArticleService(); 
		List<Article> articles =  articleService.loadArticles(false);
		
		try {
			request.setAttribute("articles", articles);
			request.setAttribute("category", category);
			request.setAttribute("categories", categories);
			request.setAttribute("items", items);
			request.getRequestDispatcher("/n_category.jsp").forward(request, response);
			
		} catch (ServletException | IOException e) {
			log.severe(e.getMessage());
			throw new CustomException(Status.NOT_FOUND, "Something went wrong.");
		}
		log.info("End view");
	}
	
	@Path("/e/{slug}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@RolesAllowed({"ADMIN", "API", "SEO"})
	public void edit( @DefaultValue("") @PathParam("slug") String slug ) {
		response.setContentType("text/html; charset=utf-8");
		
		Category category =  categoryService.loadCategory(slug); 
		List<Category> categories =  categoryService.loadAllCategories(); 
	
		
		try {
			
			request.setAttribute("category", category);
			request.setAttribute("categories", categories);
			request.getRequestDispatcher("/category_form.jsp").forward(request, response);
			
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
			@DefaultValue("1000000") @FormParam("priority") Integer  priority,
			@DefaultValue("0") @FormParam("parent") String  parent) throws IOException {
		
		log.info("Start save ");
		
		if(slug.length() <=0 ){
			throw new CustomException(Status.BAD_REQUEST, "Field 'slug' is missing.");
		}
		
		if(name.length() <=0 ){
			throw new CustomException(Status.BAD_REQUEST, "Field 'name' is missing.");
		}
		
		Category newCategory =  categoryService.loadCategory(slug);
		
		if( newCategory == null ){
			newCategory = new Category();
		}
		
		newCategory.setDescription(description);
		newCategory.setName(new String(name.getBytes("utf-8"),"utf-8" ));
		newCategory.setSlug(slug);
		newCategory.setPriority(priority);
		newCategory.setParent(parent);
		categoryService.saveCategory(newCategory);
		
		response.sendRedirect("/category/e/"+slug);
		log.info("End save ");
	}

}
