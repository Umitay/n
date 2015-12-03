package com.umi.common.services.facebook;

import lombok.Getter;
import lombok.Setter;

public class FacebookSignedRequest {
	@Getter
	@Setter
	private String algorithm;
	@Getter
	@Setter
	private Long expires;
	@Getter
	@Setter
	private Long issued_at;
	@Getter
	@Setter
	private String oauth_token;
	@Getter
	@Setter
	private Long user_id;
	@Getter
	@Setter
	private FacebookSignedRequestUser user;

	public static class FacebookSignedRequestUser {
		@Getter
		@Setter
		private String country;
		@Getter
		@Setter
		private String locale;
		@Getter
		@Setter
		private FacebookSignedRequestUserAge age;

		public static class FacebookSignedRequestUserAge {
			@Getter
			@Setter
			private int min;
			@Getter
			@Setter
			private int max;

		}

	}

}