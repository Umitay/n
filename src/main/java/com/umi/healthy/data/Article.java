package com.umi.healthy.data;

import lombok.Getter;
import lombok.Setter;

import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
@Cache
@Entity(name = "Article")
public class Article {
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
	
}
