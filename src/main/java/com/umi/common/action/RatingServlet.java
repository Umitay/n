package com.umi.common.action;

import java.util.List;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import lombok.extern.java.Log;

import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions;
import com.umi.common.data.Article;
import com.umi.common.data.Item;
import com.umi.common.data.Rating;
import com.umi.common.data.Rating;
import com.umi.common.services.ArticleService;
import com.umi.common.services.RatingService;
import com.umi.common.services.ItemService;
import com.umi.common.services.RatingService;
import com.umi.common.utils.StringUtil;

import static com.google.appengine.api.taskqueue.TaskOptions.Builder.withUrl;

@Log
@Path("/rating")
public class RatingServlet {
	
	@POST
	@Produces("text/html")
    public Response post(@FormParam("slug") String slug  , @FormParam("rating") Integer rating, @FormParam("type") String type) {
		log.info("Start RatingServlet.post" );
		log.info("slug: " +slug );
		log.info("rating: " +rating );
		log.info("type: " +type );
		
		try{
		
		RatingService irs = new RatingService();
		Rating objRating = new Rating();
		
		objRating.setRating(rating);
		objRating.setSlug(slug);
		objRating.setType(type);
		objRating.setTimestamp(System.currentTimeMillis());
		
		irs.save(objRating);
		
		TaskOptions ops = withUrl("/rating/calc/"+type+"/"+slug); 
	/*	ops.param("slug", slug ); 
		ops.param("rating", rating );     */
		QueueFactory.getQueue("calculateRating").add(ops);
		
		}catch(Exception e){
			log.severe(StringUtil.exceptionFormat( e ));
		}
		
		log.info("End RatingServlet.post");
		return Response.ok().build();
    }

	
	@Path("/calc/{type}/{slug}")
	@POST
	@Produces("text/html")
    public Response set( @DefaultValue("") @PathParam("slug") String slug, @DefaultValue("") @PathParam("type") String type) {
		log.info("Start RatingServlet.set" );
		log.info("slug: " +slug );
		Integer sum = 0;
		Integer amount = 0;
		Integer itemRate = 0;
		try{
			RatingService irs = new RatingService();
			List<Rating> RatingList1 = irs.loadByRateSlug( slug, 1 );
			List<Rating> RatingList2 = irs.loadByRateSlug( slug, 2 );
			List<Rating> RatingList3 = irs.loadByRateSlug( slug, 3 );
			List<Rating> RatingList4 = irs.loadByRateSlug( slug, 4 );
			List<Rating> RatingList5 = irs.loadByRateSlug( slug, 5 );
			
			if(RatingList1 != null && RatingList1.size() >0){
				for( Rating rate:RatingList1){
					sum+=rate.getRating();
				}
				 amount += RatingList1.size();
			}
			if(RatingList2 != null && RatingList2.size() >0){
				for( Rating rate:RatingList2){
					sum+=rate.getRating();
				}
				 amount += RatingList2.size();
			}
			if(RatingList3 != null && RatingList3.size() >0){
				for( Rating rate:RatingList3){
					sum+=rate.getRating();
				}
				 amount += RatingList3.size();
			}
			if(RatingList4 != null && RatingList4.size() >0){
				for( Rating rate:RatingList4){
					sum+=rate.getRating();
				}
				 amount += RatingList4.size();
			}
			if(RatingList5 != null && RatingList5.size() >0){
				for( Rating rate:RatingList5){
					sum+=rate.getRating();
				}
				 amount += RatingList5.size();
			}
			
			if(amount > 0 && sum >0 ){
				itemRate = sum/amount;
				
				if("item".equals(type)){
					ItemService itemService = new ItemService(); 
					Item  item = itemService.loadItem(slug);
					item.setRating(itemRate);
					item.setDateModified(System.currentTimeMillis());
					itemService.save(item);
				}else if("article".equals(type)){
					ArticleService articleService = new ArticleService(); 
					Article  article = articleService.loadArticle(slug);
					article.setRating(itemRate);
					article.setDateModified(System.currentTimeMillis());
					articleService.save(article);
				}
			}
			
		}catch(Exception e){
			log.severe(StringUtil.exceptionFormat( e ));
		}
		
		log.info("End RatingServlet.set");
		return Response.ok().build();
    }
}