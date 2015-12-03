package com.umi.common.action;

import java.io.IOException;
import java.util.Collections;
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
import com.umi.common.data.Article;
import com.umi.common.data.Category;
import com.umi.common.data.Item;
import com.umi.common.data.X_CategoryItem;
import com.umi.common.services.ArticleService;
import com.umi.common.services.CategoryService;
import com.umi.common.services.ItemService;
import com.umi.common.utils.CustomException;
import com.umi.common.utils.StringUtil;

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
			if(StringUtil.is_rus(slug) ){
				slug = StringUtil.generateSlug(slug);
				response.sendRedirect("/article/"+slug);
			}else{
				response.sendRedirect("/article/list");
				throw new CustomException(Status.NOT_FOUND, "404");
			}
		}
		
		List<Article> articles =  articleService.loadArticles(true);
		CategoryService categoryService = new CategoryService(); 
		List<Category> categories =  categoryService.loadTopCategories(); 
		ItemService itemService = new ItemService(); 
		List<Item>  items = itemService.loadItems(16,0);
		Collections.shuffle(items);
		
		String meta_description=article.getMeta_description();
		if(meta_description == null || meta_description.length() <=0){
			meta_description =  article.getAbout() +" Вкусно ✓ Полезно ✓ Легко ✓";
		}
		
		String meta_title = article.getMeta_title();
		if(meta_title == null || meta_title.length() <= 0 ){
			meta_title = article.getName();
		}
		String meta_keywords = article.getMeta_keywords();
		if(meta_keywords == null || meta_keywords.length() <= 0 ){
			meta_keywords = article.getName() +" Вкусно ✓ Полезно ✓ Легко ✓";
		}
		
		try {
			request.setAttribute("articles", articles);
			request.setAttribute("article", article);
			request.setAttribute("items", items);
			request.setAttribute("categories", categories);
			request.setAttribute("meta_title",  meta_title );
			request.setAttribute("meta_keywords", meta_keywords );
			request.setAttribute("meta_description", meta_description);
		
			request.getRequestDispatcher("/article/article.jsp").forward(request, response);
			
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
			request.getRequestDispatcher("/article/article_list.jsp").forward(request, response);
			
		} catch (ServletException | IOException e) {
			log.severe(e.getMessage());
			throw new CustomException(Status.NOT_FOUND, "Something went wrong.");
		}
		log.info("End view");
	}
}
