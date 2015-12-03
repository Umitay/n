package com.umi.common.data;

import lombok.Getter;
import lombok.Setter;

import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
@Cache
@Entity(name = "X_CategoryItem")
public class X_CategoryItem {
	
	@Id
	@Getter
	@Setter
	public Long  id;
	@Index
	@Getter
	@Setter
	public String  cat_slug;
	@Index
	@Getter
	@Setter
	public String  item_slug;
	@Index
	@Getter
	@Setter
	protected Long dateCreated;
	
	@Index
	@Getter
	@Setter
	protected Boolean active;
}
