package com.umi.common.services;

import static com.umi.common.data.persist.OfyService.ofy;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import lombok.extern.java.Log;

import com.google.appengine.labs.repackaged.com.google.common.collect.Lists;
import com.umi.common.data.Category;
import com.umi.common.data.Item;
import com.umi.common.data.ItemRating;
import com.umi.common.data.SitemapIndex;
import com.umi.common.data.X_CategoryItem;
import com.umi.common.data.persist.DBService;
import com.umi.common.utils.StringUtil;

@Log
public class ItemRatingService extends DBService{
	
	
	public List<ItemRating> loadBySlug( String slug   ){

		List<ItemRating>  itemRatingList = null;
		
		try{
			itemRatingList = load( ItemRating.class,"slug", slug );
		
		}catch(Exception e ) {
			log.severe(StringUtil.exceptionFormat( e ));
		}
		
		return itemRatingList;
	}

	
	}
