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
public class ItemServlet {

	@Context HttpServletRequest request;
	@Context HttpServletResponse response;
	
	ItemService itemService = new ItemService(); 
	
	@Path("/{slug}")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public Response view( @DefaultValue("") @PathParam("slug") String slug ) throws IOException, URISyntaxException {
		
		if(slug.length() <=0 ){
			throw new CustomException(Status.BAD_REQUEST, "Field 'slug' is missing.");
		}
		
		Item item =  itemService.loadItem(slug); 
		
		if( item == null ){
			if(StringUtil.is_rus(slug) ){
				
				slug = StringUtil.generateSlug(slug);
				return Response.status(Response.Status.MOVED_PERMANENTLY).location(new URI("/recipe/"+slug)).build();
			}else{
				response.sendRedirect("/404.jsp");
				throw new CustomException(Status.NOT_FOUND, "404");
			}
		}
		
		if(request.getServerName().contains("appspot.com")){
			request.setAttribute("unvisible", true);
		}
		
		ItemService itemService = new ItemService(); 
		List<Item>  items = itemService.loadItems(100,0);
		Collections.shuffle(items);
		
		CategoryService categoryService = new CategoryService(); 
		ArticleService articleService = new ArticleService(); 
		
		List<Article> articles = articleService.loadArticles(true);
		Collections.shuffle(articles);
		
		List<Category> categories =  categoryService.loadTopCategories(); 
		List<Category> all_categories =  categoryService.loadAllCategories(); 
		List<Category> item_categories =  Lists.newArrayList();
		
		for (Category cat : all_categories) {
			if(item.getCategories().contains( cat.getSlug() )){
				item_categories.add(cat);
			}
		}
		
		String meta_description=item.getMeta_description();
		if(meta_description == null || meta_description.length() <=0){
			meta_description =  item.getAbout();
		}
		
		String meta_title = item.getMeta_title();
		if(meta_title == null || meta_title.length() <= 0 ){
			meta_title = item.getName();
		}
		String meta_keywords = item.getMeta_keywords();
		if(meta_keywords == null || meta_keywords.length() <= 0 ){
			meta_keywords = item.getName();
		}
		
		try {
			Date d = new Date( item.getDatePublished() );
			request.setAttribute("item_datePublished", DateFormatUtils.format(d,"dd.MM.yyyy"));
			
			Date dm = new Date( item.getDateModified() );
			request.setAttribute("item_dateModified", DateFormatUtils.format(dm,"dd.MM.yyyy"));
			
			request.setAttribute("categories", categories);
			request.setAttribute("item_categories", item_categories);
			request.setAttribute("item", item);
			request.setAttribute("articles", articles);
			request.setAttribute("items", items);
			request.setAttribute("meta_title", meta_title);
			request.setAttribute("meta_keywords", meta_keywords);
			request.setAttribute("meta_description", meta_description);
			
			request.getRequestDispatcher("/item/item.jsp").forward(request, response);
			
		} catch (ServletException | IOException e) {
			log.severe(e.getMessage());
			throw new CustomException(Status.NOT_FOUND, "Something went wrong.");
		}
		return Response.ok().build();
	}
}
