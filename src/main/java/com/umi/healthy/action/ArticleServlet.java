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
import com.umi.healthy.utils.StringUtil;

@Path("/article")
@Log
@PermitAll
public class ArticleServlet {

	@Context HttpServletRequest request;
	@Context HttpServletResponse response;
	ArticleService articleService = new ArticleService(); 
	
	
	@Path("/{slug}")
	@GET
	public void view( @DefaultValue("") @PathParam("slug") String slug ) throws IOException {
		log.info("Start view");
		if(slug.length() <=0 ){
			response.sendRedirect("/");
			throw new CustomException(Status.BAD_REQUEST, "Field 'slug' is missing.");
		}
		if(request.getServerName().contains("appspot.com")){
			request.setAttribute("unvisible", true);
		}
		
		Article article =  articleService.loadArticle(slug); 
		if( article == null ){
			response.sendRedirect("/");
			throw new CustomException(Status.NOT_FOUND, "Something went wrong.");
		}
		List<Article> articles =  articleService.loadArticles(true);
		CategoryService categoryService = new CategoryService(); 
		List<Category> categories =  categoryService.loadTopCategories(); 
		
		try {
			request.setAttribute("articles", articles);
			request.setAttribute("article", article);
			request.setAttribute("categories", categories);
			request.getRequestDispatcher("/article.jsp").forward(request, response);
			
		} catch (ServletException | IOException e) {
			log.severe(e.getMessage());
			throw new CustomException(Status.NOT_FOUND, "Something went wrong.");
		}
		log.info("End view");
	}
	@Path("/list")
	@GET
	public void list( ) {
		log.info("Start list");
		if(request.getServerName().contains("appspot.com")){
			request.setAttribute("unvisible", true);
		}
		CategoryService categoryService = new CategoryService(); 
		List<Category> categories =  categoryService.loadTopCategories();
		
		ArticleService articleService = new ArticleService(); 
		List<Article> articles =  articleService.loadArticles(true);
		Category category =  categoryService.loadCategory("articles");
		try {
			request.setAttribute("category", category);
			request.setAttribute("articles", articles);
			request.setAttribute("categories", categories);
			request.getRequestDispatcher("/article_list.jsp").forward(request, response);
			
		} catch (ServletException | IOException e) {
			log.severe(e.getMessage());
			throw new CustomException(Status.NOT_FOUND, "Something went wrong.");
		}
		log.info("End view");
	}

	@Path("/l")
	@GET
	public void n_list( ) {
		log.info("Start n_list");
		
		CategoryService categoryService = new CategoryService(); 
		List<Category> categories =  categoryService.loadTopCategories();
		
		ArticleService articleService = new ArticleService(); 
		List<Article> articles =  articleService.loadArticles(false);
		
		try {
			request.setAttribute("articles", articles);
			request.setAttribute("categories", categories);
			request.getRequestDispatcher("/n_article.jsp").forward(request, response);
			
		} catch (ServletException | IOException e) {
			log.severe(e.getMessage());
			throw new CustomException(Status.NOT_FOUND, "Something went wrong.");
		}
		log.info("End view");
	}
	
	@Path("/e/{slug}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@RolesAllowed({"ADMIN", "API"})
	public void edit( @DefaultValue("") @PathParam("slug") String slug ) throws IOException {
		response.setContentType("text/html; charset=utf-8");
		
		Article article =  articleService.loadArticle(slug); 
		
		CategoryService categoryService = new CategoryService(); 
		List<Category> categories =  categoryService.loadAllCategories(); 
		
		try {
			request.setAttribute("article", article);
			request.setAttribute("categories", categories);
			request.getRequestDispatcher("/article_form.jsp").forward(request, response);
			
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
			@DefaultValue("") @FormParam("thumbnailUrl") String thumbnailUrl,
			@DefaultValue("") @FormParam("about") String  about,
			@DefaultValue("false") @FormParam("active") Boolean active,
			@DefaultValue("") @FormParam("description") String  description
			) throws IOException {
		
		log.info("Start save ");
		
		if(name.length() <=0 ){
			response.sendRedirect("/n");
			throw new CustomException(Status.BAD_REQUEST, "Field 'name' is missing.");
		}
		
		if(slug.length() <=0 ){
			slug = StringUtil.rus2lat(name.toLowerCase());
			slug = slug.trim();
			slug = slug.replace(",", "");
			slug = slug.replace(".", "");
			slug = slug.replace(" ", "-");
			slug = slug.replace("--", "-");
		}
		
		Article newarticle =  articleService.loadArticle(slug); 
		
		if( newarticle == null ){
			newarticle = new Article();
		}
		
		newarticle.setDescription(description.trim());
		newarticle.setName(new String(name.getBytes("utf-8"),"utf-8" ));
		newarticle.setSlug(slug);
		newarticle.setThumbnailUrl(thumbnailUrl.trim());
		newarticle.setAbout(about.trim());
		newarticle.setActive(active);
		articleService.saveArticle(newarticle);
		
		response.sendRedirect("/article/l");
		log.info("End save ");
	}

}
