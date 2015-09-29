package com.umi.healthy.data;

import lombok.Getter;
import lombok.Setter;

import com.googlecode.objectify.annotation.Id;

public class X_CategoryItem {
	@Id
	@Getter
	@Setter
	public String  item_slug;
	
	@Getter
	@Setter
	public String  cat_slug;
	
}
