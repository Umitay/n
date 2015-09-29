package com.umi.healthy.data.meta;

import lombok.Getter;
import lombok.Setter;

import com.googlecode.objectify.annotation.Index;

public class CategoryMeta {
	@Getter
	@Setter
	public String  slug;
	
	
	@Getter
	@Setter
	public String name;

	
	@Getter
	@Setter
	public String description;
	
	@Getter
	@Setter
	public Integer priority;
}
