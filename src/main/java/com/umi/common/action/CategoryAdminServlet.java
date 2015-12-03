package com.umi.common.action;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
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

import lombok.extern.java.Log;

import com.google.appengine.repackaged.com.google.api.client.util.Lists;
import com.umi.common.data.Article;
import com.umi.common.data.Category;
import com.umi.common.data.Item;
import com.umi.common.services.ArticleService;
import com.umi.common.services.CategoryService;
import com.umi.common.services.ItemService;
import com.umi.common.utils.CustomException;
import com.umi.common.utils.StringUtil;

@Path("/n/category")
@Log
@PermitAll
public class CategoryAdminServlet {

	@Context HttpServletRequest request;
	@Context HttpServletResponse response;
	
	CategoryService categoryService = new CategoryService(); 
	
	
	

	@Path("/{slug}")
	@GET
	public void view( @DefaultValue("") @PathParam("slug") String slug ) throws IOException {
		log.info("Start view");
		if(slug.length() <=0 ){
			throw new CustomException(Status.BAD_REQUEST, "Field 'slug' is missing.");
		}
		
		CategoryService categoryService = new CategoryService(); 
		Category category =  categoryService.loadCategory(slug); 
		
		
		if( category == null ){
			response.sendRedirect("/");
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
			request.getRequestDispatcher("/n/category.jsp").forward(request, response);
			
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
	public void edit( @DefaultValue("") @PathParam("slug") String slug ) throws IOException {
		log.info("Start edit: "+slug);
		if(slug.length() <=0 ){
			throw new CustomException(Status.BAD_REQUEST, "Field 'slug' is missing.");
		}
		
		response.setContentType("text/html; charset=utf-8");
		CategoryService categoryService = new CategoryService(); 
		Category category =  categoryService.loadCategory(slug); 
		log.info("Category name: "+category.getName());
		
		List<Category> categories =  categoryService.loadAllCategories(); 
		log.info("Categories: "+categories.size());
		
		try {
			
			request.setAttribute("category", category);
			request.setAttribute("categories", categories);
			request.getRequestDispatcher("/n/category_form.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			log.severe(e.getMessage());
			response.sendRedirect("/n");
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
			@DefaultValue("0") @FormParam("parent") String  parent,
			 @DefaultValue("") @FormParam("link_title") String link_title, 
			 @DefaultValue("") @FormParam("meta_title") String meta_title,
			 @DefaultValue("") @FormParam("meta_keywords") String meta_keywords, 
			 @DefaultValue("") @FormParam("meta_description") String meta_description,
			 @DefaultValue("") @FormParam("ads_jumbotron") String ads_jumbotron,
			 @DefaultValue("") @FormParam("ads_horizont1") String ads_horizont1, 
			 @DefaultValue("") @FormParam("ads_horizont2") String ads_horizont2
			) throws IOException {
		
		log.info("Start save ");
		
		
		
		if(name.length() <=0 ){
			response.sendRedirect("/n");
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
		newCategory.setLink_title(link_title);
		newCategory.setMeta_title(meta_title);
		newCategory.setMeta_keywords(meta_keywords);
		newCategory.setMeta_description(meta_description);
		newCategory.setAds_jumbotron(ads_jumbotron);
		newCategory.setAds_horizont1(ads_horizont1);
		newCategory.setAds_horizont2(ads_horizont2);
		
		categoryService.saveCategory(newCategory);
		
		response.sendRedirect("/n/category/e/"+slug);
		log.info("End save ");
	}

}
