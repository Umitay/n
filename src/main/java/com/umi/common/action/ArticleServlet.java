package com.umi.common.action;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
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

@Path("/article")
@Log
@PermitAll
public class ArticleServlet extends BaseServlet {

	@GET
	@Produces(MediaType.TEXT_HTML)
	public Response index()throws IOException, ServletException{
		log.info("1");
		response.setStatus(Response.Status.BAD_REQUEST.getStatusCode());
		request.getRequestDispatcher("/404.jsp").forward(request, response);
		return Response.status(Response.Status.BAD_REQUEST.getStatusCode()).build();
	}
	
	@Path("/{slug}")
	@GET
	public Response view(@PathParam("slug") String slug ) throws IOException, ServletException, URISyntaxException {
		log.info("Start view");
		
		if(request.getServerName().contains("appspot.com")){
			request.setAttribute("unvisible", true);
		}
		Article article =  articleService.loadArticle(slug); 
		
		if( article == null ){
			if(StringUtil.is_rus(slug) ){
				slug = StringUtil.generateSlug(slug);
				return Response.status(Response.Status.MOVED_PERMANENTLY).location(new URI("/article/"+slug)).build();
			}

			response.setStatus(Response.Status.NOT_FOUND.getStatusCode());
			request.getRequestDispatcher("/404.jsp").forward(request, response);
			return Response.status(Response.Status.NOT_FOUND.getStatusCode()).build();
		}
		
		try {
			Date d = new Date( article.getDatePublished() );
			request.setAttribute("article_datePublished", DateFormatUtils.format(d,"yyyy-MM-dd"));
			
			Date dm = new Date( article.getDateModified() );
			request.setAttribute("article_dateModified", DateFormatUtils.format(dm,"yyyy-MM-dd"));
			
			request.setAttribute("articles", articles);
			request.setAttribute("article", article);
			request.setAttribute("items", items);
			request.setAttribute("categories", categories);
			request.setAttribute("meta_title",   article.getMeta_title() );
			request.setAttribute("meta_keywords", article.getMeta_keywords() );
			request.setAttribute("meta_description", article.getMeta_description());
			request.getRequestDispatcher("/article/article.jsp").forward(request, response);
			
		} catch (ServletException | IOException e) {
			log.severe(e.getMessage());
			throw new CustomException(Status.NOT_FOUND, "Something went wrong.");
		}
		log.info("End view");
		return Response.ok().build();
	}
	@Path("/list")
	@GET
	public Response list( ) {
		log.info("Start list");
		if(request.getServerName().contains("appspot.com")){
			request.setAttribute("unvisible", true);
		}
		
		Category category =  categoryService.loadCategory("articles"); 
		articles   =  articleService.loadArticles(true);
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
	
		return Response.ok().build();
	}
}
