package com.umi.common.action;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import lombok.extern.java.Log;

import com.umi.common.data.ItemRating;
import com.umi.common.services.ItemRatingService;
import com.umi.common.utils.StringUtil;

@Log
@Path("/rating")
public class RatingServlet {
	@POST
	@Produces("text/html")
    public Response post(@FormParam("slug") String slug  ,@FormParam("rating") Integer rating) {
		log.info("Start RatingServlet.post" );
		log.info("item_id: " +slug );
		log.info("rating: " +rating );
		try{
		ItemRatingService irs = new ItemRatingService();
		ItemRating itemRating = new ItemRating();
		
		itemRating.setRating(rating);
		itemRating.setSlug(slug);
		itemRating.setTimestamp(System.currentTimeMillis());
		
		irs.save(itemRating);
		}catch(Exception e){
			log.severe(StringUtil.exceptionFormat( e ));
		}
		
		log.info("End RatingServlet.post");
		return Response.ok().build();
    }
	
}