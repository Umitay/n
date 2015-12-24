package com.umi.common.data;

import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

import lombok.Getter;
import lombok.Setter;

public class User {
	
	@Id
	@Getter
	@Setter
	public String  identifier;
	
	@Getter
	@Setter
	protected String email;
	
	@Getter
	@Setter
	protected String password;
	
	@Index
	@Getter
	@Setter
	protected Boolean active;
	
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
	protected Boolean is_subscribed = false;
	
}
