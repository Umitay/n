package com.umi.common.services;

import static com.umi.common.data.persist.OfyService.ofy;

import java.util.List;

import lombok.extern.java.Log;

import com.umi.common.data.Rating;
import com.umi.common.data.persist.DBService;
import com.umi.common.utils.StringUtil;

@Log
public class RatingService extends DBService{
	
	
	public List<Rating> loadBySlug( String slug   ){

		List<Rating>  RatingList = null;
		
		try{
			RatingList = load( Rating.class,"slug", slug );
		
		}catch(Exception e ) {
			log.severe(StringUtil.exceptionFormat( e ));
		}
		
		return RatingList;
	}
	
	
	public List<Rating> loadByRateSlug( String slug, Integer rating ){

		List<Rating>  RatingList = null;
		
		try{
			RatingList = ofy().load().type(Rating.class).filter("slug", slug).filter("rating", rating).list();
		
		}catch(Exception e ) {
			log.severe(StringUtil.exceptionFormat( e ));
		}
		
		return RatingList;
	}
	}
