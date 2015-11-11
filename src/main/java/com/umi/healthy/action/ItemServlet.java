package com.umi.healthy.action;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
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
import com.umi.healthy.data.Article;
import com.umi.healthy.data.Category;
import com.umi.healthy.data.Item;
import com.umi.healthy.data.X_CategoryItem;
import com.umi.healthy.services.ArticleService;
import com.umi.healthy.services.CategoryService;
import com.umi.healthy.services.ItemService;
import com.umi.healthy.utils.CustomException;
import com.umi.healthy.utils.StringUtil;

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
		
		CategoryService categoryService = new CategoryService(); 
		ArticleService articleService = new ArticleService(); 
		List<Article> articles =  articleService.loadArticles(true);
		
		List<Category> categories =  categoryService.loadTopCategories(); 
		List<Category> all_categories =  categoryService.loadAllCategories(); 
		List<Category> item_categories =  Lists.newArrayList();
		
		for (Category cat : all_categories) {
			if(item.getRecipeCategory().contains( cat.getSlug() )){
				item_categories.add(cat);
			}
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
			
			request.setAttribute("meta_title", item.getMeta_title()!=null ? item.getMeta_title():  item.getName() );
			request.setAttribute("meta_keywords", item.getMeta_keywords()!=null  ? item.getMeta_keywords():  item.getName() +"Вкусно ✓ Полезно ✓ Легко ✓");
			request.setAttribute("meta_description", item.getMeta_description()!=null  ? item.getMeta_description() +"Вкусно ✓ Полезно ✓ Легко ✓":  item.getAbout()  +"Вкусно ✓ Полезно ✓ Легко ✓");
			
			request.getRequestDispatcher("/item.jsp").forward(request, response);
			
		} catch (ServletException | IOException e) {
			log.severe(e.getMessage());
			throw new CustomException(Status.NOT_FOUND, "Something went wrong.");
		}
		return Response.ok().build();
	}

	@Path("/e/{slug}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@RolesAllowed({"ADMIN", "API"})
	public void edit( @DefaultValue("") @PathParam("slug") String slug ) {
		
		String item_categories = "";
		Item item =  itemService.loadItem(slug); 
		if(item !=null){
			item_categories = StringUtils.join( item.getRecipeCategory(), ",");
		}
		
		CategoryService categoryService = new CategoryService(); 
		List<Category> all_categories =  categoryService.loadAllCategories(); 
		
	
		
		try {
			request.setAttribute("categories", all_categories);
			request.setAttribute("item_categories", item_categories );
			request.setAttribute("item", item);
			if(request.getServerName().contains("appspot.com")){
				request.setAttribute("is_admin", true);
			}
			request.getRequestDispatcher("/item_form.jsp").forward(request, response);
			
		} catch (ServletException | IOException e) {
			log.severe(e.getMessage());
			throw new CustomException(Status.NOT_FOUND, "Something went wrong.");
		}
	}
	
	@Path("/save")
	@POST
	@Consumes("application/x-www-form-urlencoded")
	@RolesAllowed({"ADMIN", "API", "SEO"})
	public void save (	
			 @DefaultValue("") @FormParam("slug") String  slug,
			 @DefaultValue("") @FormParam("name") String name,
			 @DefaultValue("") @FormParam("alt") String alt,
			 @DefaultValue("") @FormParam("thumbnailUrl") String thumbnailUrl,
			 @DefaultValue("") @FormParam("thumbnailUrl2") String thumbnailUrl2,
			 @DefaultValue("") @FormParam("about") String about,
			 @DefaultValue("") @FormParam("description") String description,
			 @DefaultValue("") @FormParam("recipeCategory") String recipeCategory,
			 @DefaultValue("") @FormParam("totalTime") String totalTime,
			 @DefaultValue("") @FormParam("recipeYield") String recipeYield,
			 @DefaultValue("") @FormParam("ingredients") String ingredients,
			 @DefaultValue("") @FormParam("nutrition") String  nutrition,
			 @DefaultValue("") @FormParam("active") Boolean active,
			 @DefaultValue("") @FormParam("datePublished") Long  datePublished,
			 @DefaultValue("") @FormParam("dateCreated") Long dateCreated,
			 @DefaultValue("") @FormParam("dateModified") Long dateModified,
			 @DefaultValue("") @FormParam("fb_share")	String fb_share ,
			 @DefaultValue("") @FormParam("vk_share") String vk_share ,
			 @DefaultValue("") @FormParam("lj_share") String lj_share ,
			 @DefaultValue("") @FormParam("twitter_share") String twitter_share,
			 @DefaultValue("") @FormParam("link_title") String link_title, 
			 @DefaultValue("") @FormParam("meta_title") String meta_title,
			 @DefaultValue("") @FormParam("meta_keywords") String meta_keywords, 
			 @DefaultValue("") @FormParam("meta_description") String meta_description
			 ) throws IOException {
		
		log.info("Start save with slug: "+slug);
	
		if(name.length() <=0 ){
			response.sendRedirect("/n");
			throw new CustomException(Status.BAD_REQUEST, "Field 'name' is missing.");
		}
		
		if(slug.length() >0 ){
			
			Item item = itemService.loadItem(slug);
			
			if( item != null ) {
				log.info("Found the item with slug: " + slug);
				
				if(!item.getName().equals(name) ){
					log.info(" Found an Item by given slug, but name of the Item was changed, therefor will be deleted and than will created with new generated slug.");
					List<X_CategoryItem> x  = itemService.load(X_CategoryItem.class, "item_slug", item.getSlug() );
					if(!x.isEmpty() ){
						log.info("X_CategoryItem size: " + x.size() );
						itemService.deleteList(x);
					}
					log.info("start delete the item with slug: " + slug);
					itemService.delete(item);
			}
			}
		}
		slug = StringUtil.generateSlug(name);
		
		Boolean is_admin = false;
		if(request.getServerName().contains("appspot.com")){
			is_admin = true;
		}
		
		itemService.saveItem(slug,name,alt,thumbnailUrl,thumbnailUrl2,about,description,
					recipeCategory,totalTime,recipeYield,ingredients,nutrition,
					active,datePublished,dateCreated,dateModified,
					fb_share, vk_share, lj_share, twitter_share,is_admin, link_title, meta_title, meta_keywords, meta_description);
		  
		response.sendRedirect("/n");
		log.info("End save ");
	}

}
