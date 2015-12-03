package com.umi.common.data;

import lombok.Getter;
import lombok.Setter;

import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Cache
@Entity(name = "Category")
public class Category {
	
	@Id
	@Getter
	@Setter
	public String  slug;
	
	@Index
	@Getter
	@Setter
	public String name;

	@Index
	@Getter
	@Setter
	public String description;
	
	@Index
	@Getter
	@Setter
	public Integer priority;
	
	@Index
	@Getter
	@Setter
	public String parent;
	
	@Index
	@Getter
	@Setter
	protected Boolean active;
	
	@Index
	@Getter
	@Setter
	protected Long  datePublished;
	@Index
	@Getter
	@Setter
	protected Long dateCreated;
	
	@Index
	@Getter
	@Setter
	protected Long dateModified;
	@Index
	@Getter
	@Setter
	public String   link_title;
	@Index
	@Getter
	@Setter
	public String  meta_title;
	
	@Index
	@Getter
	@Setter
	public String  meta_keywords;
	
	@Index
	@Getter
	@Setter
	public String  meta_description;
	
	@Index
	@Getter
	@Setter
	public String ads_jumbotron; 
	
	@Index
	@Getter
	@Setter
	public String ads_horizont1;
	
	@Index
	@Getter
	@Setter
	public String ads_horizont2;
}
