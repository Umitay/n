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

@Path("/category")
@Log
@PermitAll
public class CategoryServlet{

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
			request.getRequestDispatcher("/category/category_list.jsp").forward(request, response);
			
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
					slug = "maicourse";
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

		String meta_description=category.getMeta_description();
		if(meta_description == null || meta_description.length() <=0){
			meta_description = "Откройте для себя полезные, легкие и вкусные рецепты.";
		}
		
		String meta_title = category.getMeta_title();
		if(meta_title == null || meta_title.length() <= 0 ){
			meta_title = category.getName();
		}
		String meta_keywords = category.getMeta_keywords();
		if(meta_keywords == null || meta_keywords.length() <= 0 ){
			meta_keywords = category.getName();
		}
		
		try {
			request.setAttribute("articles", articles);
			request.setAttribute("category", category);
			request.setAttribute("categories", categories);
			request.setAttribute("items", items);
			request.setAttribute("meta_title",  meta_title );
			request.setAttribute("meta_keywords", meta_keywords );
			request.setAttribute("meta_description", meta_description);
			request.getRequestDispatcher("/category/category.jsp").forward(request, response);
			
		} catch (ServletException | IOException e) {
			log.severe(e.getMessage());
			response.sendRedirect("/");
			throw new CustomException(Status.NOT_FOUND, "Something went wrong.");
		}
		log.info("End view");
		return Response.ok().build();
	}
}
