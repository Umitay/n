package com.umi.common.action;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;

import com.umi.common.data.Article;
import com.umi.common.data.Category;
import com.umi.common.data.Item;
import com.umi.common.services.ArticleService;
import com.umi.common.services.CategoryService;
import com.umi.common.services.ItemService;

public class BaseServlet {
	@Context HttpServletRequest request;
	@Context HttpServletResponse response;
	CategoryService categoryService = new CategoryService(); 
	ItemService itemService = new ItemService(); 
	ArticleService articleService = new ArticleService(); 
	
	List<Article> articles =  null;
	List<Category> categories =  null;
	List<Item>  items =  null;
	
	public BaseServlet() {
		super();
		categories =  categoryService.loadTopCategories(); 
		articles   =  articleService.loadArticles(true);
		items 	   = itemService.loadItems(16,0);
		
		Collections.shuffle(items);
		Collections.shuffle(articles);	
	}
	
}
