package com.umi.common.data;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity(name = "Rating")
public class Rating {

	
	@Id
	@Getter
	@Setter
	public Long  id;
	@Index
	@Getter
	@Setter
	public String slug;
	@Index
	@Getter
	@Setter
	public String type;
	
	@Index
	@Getter
	@Setter
	public Integer rating;
	
	@Index
	@Getter
	@Setter
	protected Long timestamp;
	
}
