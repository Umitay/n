package com.umi.common.action;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.lang3.time.DateFormatUtils;

import lombok.extern.java.Log;

import com.umi.common.data.Article;
import com.umi.common.data.Category;
import com.umi.common.data.Item;
import com.umi.common.data.SitemapIndex;
import com.umi.common.data.persist.DBService;
import com.umi.common.data.persist.EnvironmentConfig;
import com.umi.common.services.ArticleService;
import com.umi.common.services.CategoryService;
import com.umi.common.services.ItemService;
import com.umi.common.utils.CustomException;
import com.umi.common.utils.StringUtil;
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
			Category category =  categoryService.loadCategory("hp");
			
			ItemService itemService = new ItemService(); 
			List<Item>  items = itemService.loadItems(16,0);
			
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
	@Path("/{slug}") // hvost
	@GET
	public void view( @DefaultValue("") @PathParam("slug") String slug ) throws IOException {
		try {
			if(StringUtil.is_rus(slug) ){
				slug = StringUtil.generateSlug(slug);
				response.sendRedirect("/"+EnvironmentConfig.getInstance().getItem_type()+"/"+slug);
			}else{
				response.sendRedirect("/");
				throw new CustomException(Status.NOT_FOUND, "404");
			}
		} catch (Exception e) {
			throw new CustomException(Status.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}
	
	
	
	@Path("sitemap_index.xml")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public String sitemap_index(  ) {
		
		DBService db = new DBService();
		SitemapIndex sitemap = db.load(SitemapIndex.class, "1");
		String xml = "<?xml version='1.0' encoding='UTF-8'?>"
				+"<sitemapindex xmlns='http://www.sitemaps.org/schemas/sitemap/0.9'>";
		
		if( sitemap!= null){
		
			if(sitemap.getCategory_date_modified()!=null){
				Date dCategory = new Date( sitemap.getCategory_date_modified());
				 xml =	xml+"<sitemap>"
				+"<loc>http://"+EnvironmentConfig.getInstance().getPublicDomain()+"/categories-sitemap.xml</loc>"
				+"<lastmod>"+DateFormatUtils.format(dCategory,"yyyy-MM-dd'T'HH:mm:ssZZ")+"</lastmod>"
				+"</sitemap>";
			}
			
			if(sitemap.getArticle_date_modified()!=null){
				Date dArticle = new Date( sitemap.getArticle_date_modified() );
				 xml =	xml+"<sitemap>"
				+"<loc>http://"+EnvironmentConfig.getInstance().getPublicDomain()+"/articles-sitemap.xml</loc>"
				+"<lastmod>"+DateFormatUtils.format(dArticle,"yyyy-MM-dd'T'HH:mm:ssZZ")+"</lastmod>"
				+"</sitemap>";
			}
			
			if(sitemap.getRecipe_date_modified()!=null){
				Date dRecipe = new Date( sitemap.getRecipe_date_modified() );
				 xml =	xml+"<sitemap>"
				+"<loc>http://"+EnvironmentConfig.getInstance().getPublicDomain()+"/items-sitemap.xml</loc>"
				+"<lastmod>"+DateFormatUtils.format(dRecipe,"yyyy-MM-dd'T'HH:mm:ssZZ")+"</lastmod>"
				+"</sitemap>";
			}
			
			if(sitemap.getImage_date_modified()!=null){
				Date dImage = new Date( sitemap.getImage_date_modified() );
				 xml =	xml+"<sitemap>"
				+"<loc>http://"+EnvironmentConfig.getInstance().getPublicDomain()+"/images-sitemap.xml</loc>"
				+"<lastmod>"+DateFormatUtils.format(dImage,"yyyy-MM-dd'T'HH:mm:ssZZ")+"</lastmod>"
				+"</sitemap>";
			}
		}
		xml = xml +"</sitemapindex>";
		
		return xml;
	}
	@Path("categories-sitemap.xml")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public String categories(  ) {
		CategoryService categoryService = new CategoryService(); 
		List<Category> categories =  categoryService.loadTopCategories();
		
		String xml = "<?xml version='1.0' encoding='UTF-8'?>"
				+"<urlset xmlns='http://www.sitemaps.org/schemas/sitemap/0.9'>";
				
		for(Category category:categories){
			Date dCategory = new Date( category.getDateModified() );
			if(!category.getSlug().equals("hp") && !category.getSlug().equals("articles")){
			xml = xml +"<url>"
				  +"<loc>"+ "http://"+EnvironmentConfig.getInstance().getPublicDomain()+ "/category/"+category.getSlug() + "</loc>"
				  +"<lastmod>"+ DateFormatUtils.format(dCategory,"yyyy-MM-dd'T'HH:mm:ssZZ") + "</lastmod>"
				  +"</url>";
			}
		}
				
	  	
	  	xml = xml+ "</urlset>";
		return xml;
		
	}	
	@Path("articles-sitemap.xml")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public String articles(  ) {

		String xml = "<?xml version='1.0' encoding='UTF-8'?>"
				+"<urlset xmlns='http://www.sitemaps.org/schemas/sitemap/0.9'>";
		
		ArticleService articleService = new ArticleService(); 
		List<Article>  articles = articleService.loadArticles(true,1000000,0);
		
		for(Article article:articles){
			Date dArticle = new Date( article.getDateModified() );
			xml = xml +"<url>"
				  +"<loc>"+ "http://"+EnvironmentConfig.getInstance().getPublicDomain()+"/article/"+article.getSlug() + "</loc>"
				  +"<lastmod>"+ DateFormatUtils.format(dArticle,"yyyy-MM-dd'T'HH:mm:ssZZ") + "</lastmod>"
				  +"</url>";
		}
				
	  	
	  	xml = xml+ "</urlset>";
		return xml;
	}	
	
	@Path("items-sitemap.xml")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public String recipes(  ) {

		String xml = "<?xml version='1.0' encoding='UTF-8'?>"
				+"<urlset xmlns='http://www.sitemaps.org/schemas/sitemap/0.9'>";
		
		ItemService itemService = new ItemService(); 
		List<Item>  items = itemService.loadItems(1000000,0);
		
		for(Item item:items){
			Date dItem = new Date( item.getDateModified() );
			xml = xml +"<url>"
				  +"<loc>"+ "http://"+EnvironmentConfig.getInstance().getPublicDomain()
				  +"/"+EnvironmentConfig.getInstance().getItem_type()
				  +"/"+item.getSlug() 
				  + "</loc>"
				  +"<lastmod>"+ DateFormatUtils.format(dItem, "yyyy-MM-dd'T'HH:mm:ssZZ") + "</lastmod>"
				  +"</url>";
		}
	  	xml = xml+ "</urlset>";
		return xml;
	}	
	@Path("images-sitemap.xml")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public String images(  ) {
		return "";
	}	
	@Path("BingSiteAuth.xml")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public String bing(  ) {
		return "<?xml version='1.0'?>"
				+"<users>"
				+"<user>61115CAA93C618E0C823FFD5262EDD80</user>"
				+"</users>";
	}	
	
	@Path("robots.txt")
	@GET
	@Produces(MediaType.TEXT_PLAIN )
	public String robots(  ) {

		String txt ="User-agent: *\nDisallow: /n\nAllow: /\nHost: "+EnvironmentConfig.getInstance().getPublicDomain()+"\nSitemap: http://"+EnvironmentConfig.getInstance().getPublicDomain()+"/sitemap_index.xml";
		
		return txt;
	}	
	
	@Path("n")
	@GET
	@RolesAllowed({"ADMIN", "API"})
	public void  n(){
		try {
			CategoryService categoryService = new CategoryService(); 
			List<Category> categories =  categoryService.loadAllCategories(); 
			ItemService itemService = new ItemService(); 
			List<Item>  items = itemService.loadItems(20,0);
			ArticleService articleService = new ArticleService(); 
			List<Article>  articles = articleService.loadArticles(true);
			request.setAttribute("categories", categories);
			request.setAttribute("items", items);
			request.setAttribute("articles", articles);
			request.getRequestDispatcher("/n/n.jsp").forward(request, response);
		} catch (Exception e) {
			throw new CustomException(Status.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}
	
	@Path("n/l")
	@GET
	@RolesAllowed({"ADMIN", "API"})
	public void  nl(){
		try {
			
			ItemService itemService = new ItemService(); 
			List<Item>  items = itemService.loadItems(1000,0);
			ArticleService articleService = new ArticleService(); 
			List<Article>  articles = articleService.loadArticles(true);
			request.setAttribute("items", items);
			request.setAttribute("articles", articles);
			request.getRequestDispatcher("/n/nl.jsp").forward(request, response);
		} catch (Exception e) {
			throw new CustomException(Status.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}}
