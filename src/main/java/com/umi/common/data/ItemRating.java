package com.umi.common.data;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Cache
@Entity(name = "ItemRating")
public class ItemRating {
	
	@Id
	@Getter
	@Setter
	public String  id;
	
	@Index
	@Getter
	@Setter
	public String slug;
	
	@Index
	@Getter
	@Setter
	public Integer rating;
	
	@Index
	@Getter
	@Setter
	protected Long timestamp;
	
}