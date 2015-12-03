package com.umi.common.data;

import lombok.Getter;
import lombok.Setter;

public class User {
	@Getter
	@Setter
	protected String email;
	
	@Getter
	@Setter
	protected String password;

	public User(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	
	
}
