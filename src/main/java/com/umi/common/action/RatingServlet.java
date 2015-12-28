package com.umi.common.action;

import java.util.logging.Logger;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;


@Path("/rating")
public class RatingServlet {
	private static final Logger log = Logger.getLogger(RatingServlet.class.getName());
	@POST
	@Produces("text/html")
    public Response post(@FormParam("slug") String slug  ,@FormParam("rating") Integer rating) {
		log.info("Start RatingServlet.post" );
		log.info("item_id: " +slug );
		log.info("rating: " +rating );
		log.info("End RatingServlet.post");
		return Response.ok().build();
    }
	
}