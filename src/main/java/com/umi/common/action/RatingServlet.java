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
import com.umi.common.data.Item;
import com.umi.common.data.ItemRating;
import com.umi.common.services.ItemRatingService;
import com.umi.common.services.ItemService;
import com.umi.common.utils.StringUtil;

import static com.google.appengine.api.taskqueue.TaskOptions.Builder.withUrl;

@Log
@Path("/rating")
public class RatingServlet {
	@POST
	@Produces("text/html")
    public Response post(@FormParam("slug") String slug  ,@FormParam("rating") Integer rating) {
		log.info("Start RatingServlet.post" );
		log.info("slug: " +slug );
		log.info("rating: " +rating );
		try{
		ItemRatingService irs = new ItemRatingService();
		ItemRating itemRating = new ItemRating();
		
		itemRating.setRating(rating);
		itemRating.setSlug(slug);
		itemRating.setTimestamp(System.currentTimeMillis());
		
		irs.save(itemRating);
		
		
		TaskOptions ops = withUrl("/rating/calc/"+slug); 
	/*	ops.param("slug", slug ); 
		ops.param("rating", rating );     */
		QueueFactory.getQueue("calculate_item_rating");//.add(ops);
		
		}catch(Exception e){
			log.severe(StringUtil.exceptionFormat( e ));
		}
		
		log.info("End RatingServlet.post");
		return Response.ok().build();
    }
	
	@Path("/calc/{slug}")
	@GET
	@Produces("text/html")
    public Response set( @DefaultValue("") @PathParam("slug") String slug) {
		log.info("Start RatingServlet.set" );
		log.info("slug: " +slug );
	
		try{
			ItemRatingService irs = new ItemRatingService();
			ItemService itemService = new ItemService(); 
			
			List<ItemRating> itemRatingList = irs.loadBySlug(slug);
			Item  item = itemService.loadItem(slug);
			
			Integer amount = itemRatingList.size();
			
			if(amount>0){
				Integer sum = 0;
				Integer itemRate = 0;
				for( ItemRating rate:itemRatingList){
					sum+=rate.getRating();
				}
				itemRate = sum/amount;
				item.setRating(itemRate);
				item.setDateModified(System.currentTimeMillis());
				itemService.save(item);
			}
			
		}catch(Exception e){
			log.severe(StringUtil.exceptionFormat( e ));
		}
		
		log.info("End RatingServlet.set");
		return Response.ok().build();
    }
}