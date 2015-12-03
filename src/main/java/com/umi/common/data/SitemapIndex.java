package com.umi.common.data;

import lombok.Getter;
import lombok.Setter;

import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
@Cache
@Entity(name = "SitemapIndex")
public class SitemapIndex {
	@Id
	@Getter
	@Setter
	public String id;

	@Index
	@Getter
	@Setter
	protected Boolean active;
	
	@Index
	@Getter
	@Setter
	protected Long category_date_modified;
	
	@Index
	@Getter
	@Setter
	protected Long article_date_modified;
	
	@Index
	@Getter
	@Setter
	protected Long recipe_date_modified;
	
	@Index
	@Getter
	@Setter
	protected Long image_date_modified;
}
