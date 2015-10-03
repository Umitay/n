package com.umi.healthy.action;

import java.io.IOException;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response.Status;

import lombok.extern.java.Log;

import com.umi.healthy.data.Article;
import com.umi.healthy.data.Category;
import com.umi.healthy.data.Item;
import com.umi.healthy.services.ArticleService;
import com.umi.healthy.services.CategoryService;
import com.umi.healthy.services.ItemService;
import com.umi.healthy.utils.CustomException;
@Path("/")
@Log
public class HomepageServlet{
	@Context HttpServletRequest request;
	@Context HttpServletResponse response;
	
	@GET
	@PermitAll
	public void  index(){
		log.info("Start index");
		try {
			CategoryService categoryService = new CategoryService(); 
			List<Category> categories =  categoryService.loadTopCategories(); 
			Category category =  categoryService.loadCategory("recipes");
			
			ItemService itemService = new ItemService(); 
			List<Item>  items = itemService.loadItems(16);
			
			ArticleService articleService = new ArticleService(); 
			List<Article>  articles = articleService.loadArticles(true);
			
			if(request.getServerName().contains("appspot.com")){
				request.setAttribute("unvisible", true);
			}
			request.setAttribute("articles", articles);
			request.setAttribute("category", category);
			request.setAttribute("categories", categories);
			request.setAttribute("items", items);
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			throw new CustomException(Status.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}
	@Path("n")
	@GET
	@RolesAllowed({"ADMIN", "API"})
	public void  n(){
		try {
			CategoryService categoryService = new CategoryService(); 
			List<Category> categories =  categoryService.loadAllCategories(); 
			ItemService itemService = new ItemService(); 
			List<Item>  items = itemService.loadItems(20);
			ArticleService articleService = new ArticleService(); 
			List<Article>  articles = articleService.loadArticles(true);
			request.setAttribute("categories", categories);
			request.setAttribute("items", items);
			request.setAttribute("articles", articles);
			request.getRequestDispatcher("/n.jsp").forward(request, response);
		} catch (Exception e) {
			throw new CustomException(Status.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}
		
}
