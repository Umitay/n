package com.umi.healthy.action;

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
import com.umi.healthy.data.Article;
import com.umi.healthy.data.Category;
import com.umi.healthy.data.Item;
import com.umi.healthy.data.X_CategoryItem;
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
			request.getRequestDispatcher("/n/n_article.jsp").forward(request, response);
			
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
			request.getRequestDispatcher("/n/article_form.jsp").forward(request, response);
			
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
			@DefaultValue("") @FormParam("alt") String alt,
			@DefaultValue("") @FormParam("thumbnailUrl") String thumbnailUrl,
			@DefaultValue("") @FormParam("about") String  about,
			@DefaultValue("false") @FormParam("active") Boolean active,
			@DefaultValue("") @FormParam("description") String  description,
			 @DefaultValue("") @FormParam("link_title") String link_title, 
			 @DefaultValue("") @FormParam("meta_title") String meta_title,
			 @DefaultValue("") @FormParam("meta_keywords") String meta_keywords, 
			 @DefaultValue("") @FormParam("meta_description") String meta_description,
			 @DefaultValue("") @FormParam("ads_horizont1") String ads_horizont1, 
			 @DefaultValue("") @FormParam("ads_horizont2") String ads_horizont2,
			 @DefaultValue("") @FormParam("ads_side1") String ads_side1, 
			 @DefaultValue("") @FormParam("ads_side2") String ads_side2
			) throws IOException {
		
		log.info("Start save ");
		
		if(name.length() <=0 ){
			response.sendRedirect("/n");
			throw new CustomException(Status.BAD_REQUEST, "Field 'name' is missing.");
		}
		
		if(slug.length() >0 ){
			
			Article article = articleService.loadArticle(slug);
			
			if(article != null && !article.getName().equals(name) ){
				log.info(" Found an Item by given slug, but name of the Item was changed, therefor will be deleted and than will created with new generated slug.");
				articleService.delete(article);
			}
		}
		
		slug = StringUtil.generateSlug(name);
		
		Article newarticle =  articleService.loadArticle(slug); 
		
		if( newarticle == null ){
			newarticle = new Article();
		}
		
		newarticle.setDescription(description.trim());
		newarticle.setName(name.trim());
		newarticle.setSlug(slug.trim());
		newarticle.setThumbnailUrl(thumbnailUrl.trim());
		newarticle.setAbout(about.trim());
		newarticle.setActive(active);
		newarticle.setAlt(alt.trim());
		newarticle.setLink_title(link_title.trim());
		newarticle.setMeta_title(meta_title.trim());
		newarticle.setMeta_keywords(meta_keywords.trim());
		newarticle.setMeta_description(meta_description.trim());
		newarticle.setAds_horizont1(ads_horizont1);
		newarticle.setAds_horizont2(ads_horizont2);
		newarticle.setAds_side1(ads_side1);
		newarticle.setAds_side2(ads_side2);
		
		articleService.saveArticle(newarticle);
		
		response.sendRedirect("/article/l");
		log.info("End save ");
	}

}
