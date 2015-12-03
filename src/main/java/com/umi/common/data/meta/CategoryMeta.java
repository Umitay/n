package com.umi.common.data.meta;

import lombok.Getter;
import lombok.Setter;

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
