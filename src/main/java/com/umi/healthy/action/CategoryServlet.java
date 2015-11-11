package com.umi.healthy.action;

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
import com.umi.healthy.data.Article;
import com.umi.healthy.data.Category;
import com.umi.healthy.data.Item;
import com.umi.healthy.services.ArticleService;
import com.umi.healthy.services.CategoryService;
import com.umi.healthy.services.ItemService;
import com.umi.healthy.utils.CustomException;
import com.umi.healthy.utils.StringUtil;

@Path("/category")
@Log
@PermitAll
public class CategoryServlet {

	@Context HttpServletRequest request;
	@Context HttpServletResponse response;
	
	CategoryService categoryService = new CategoryService(); 
	
	@GET
	public Response category_list( ) throws IOException {
		log.info("Start view");
		if(request.getServerName().contains("appspot.com")){
			request.setAttribute("unvisible", true);
		}
	
		List<Category> categories =  categoryService.loadTopCategories();
		 
		ArticleService articleService = new ArticleService(); 
		List<Article> articles =  articleService.loadArticles(true);
		
		try {
			request.setAttribute("articles", articles);
			request.setAttribute("categories", categories);
			request.getRequestDispatcher("/category_list.jsp").forward(request, response);
			
		} catch (ServletException | IOException e) {
			log.severe(e.getMessage());
			response.sendRedirect("/");
			throw new CustomException(Status.NOT_FOUND, "Something went wrong.");
		}
		log.info("End view");
		return Response.ok().build();
	}
	
	@Path("/{slug}")
	@GET
	public Response view( @DefaultValue("") @PathParam("slug") String slug ) throws IOException, URISyntaxException {
		log.info("Start view");


		if(request.getServerName().contains("appspot.com")){
			request.setAttribute("unvisible", true);
		}
		
		Category category =  categoryService.loadCategory(slug); 
		if( category == null ){
			if(StringUtil.is_rus(slug) ){
				String s = StringUtil.generateSlug(slug);
				if(s.equals("vypechka")){
					slug = "baking-recipes"; 
				}else if(s.equals("pitanie-dlya-detei")){
					slug = "healthy-kids-recipes";
				}else if(s.equals("sousy")){
					slug = "sous";
				}else if(s.equals("myasnye-blyuda")){
					slug = "main_course";
				}else if(s.equals("salaty")){
					slug = "salads";
				}else if(s.equals("ptica-i-dich")){
					slug = "chicken-recipes";
				}else if(s.equals("ovoshchi-i-garniry")){
					slug = "side-dishes";
				}else if(s.equals("supy")){
					slug = "healthy-soup-recipes";
				}else if(s.equals("desert")){
					slug = "dessert";
				}else{
					slug = s;
				}
				
				return Response.status(Response.Status.MOVED_PERMANENTLY).location(new URI("/category/"+slug)).build();
			}else{
				response.sendRedirect("/404.jsp");
				throw new CustomException(Status.NOT_FOUND, "404");
			}
		
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
			response.sendRedirect("/");
			throw new CustomException(Status.NOT_FOUND, "Something went wrong.");
		}
		log.info("End view");
		return Response.ok().build();
	}

	@Path("/l/{slug}")
	@GET
	public void n_view( @DefaultValue("") @PathParam("slug") String slug ) throws IOException {
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
	public void edit( @DefaultValue("") @PathParam("slug") String slug ) throws IOException {
		response.setContentType("text/html; charset=utf-8");
		
		Category category =  categoryService.loadCategory(slug); 
		List<Category> categories =  categoryService.loadAllCategories(); 
	
		
		try {
			
			request.setAttribute("category", category);
			request.setAttribute("categories", categories);
			request.getRequestDispatcher("/category_form.jsp").forward(request, response);
			
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
			 @DefaultValue("") @FormParam("meta_description") String meta_description
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
		categoryService.saveCategory(newCategory);
		
		response.sendRedirect("/category/e/"+slug);
		log.info("End save ");
	}

}
