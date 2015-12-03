package com.umi.common.filter;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

public class DenyAllFilter implements ContainerRequestFilter{

	@Override
	public void filter(ContainerRequestContext requestContext)
			throws IOException {
		 requestContext.abortWith(
	              Response.status( Status.FORBIDDEN )
	              .header(HttpHeaders.WWW_AUTHENTICATE, "Basic realm=\"Example\"")
	              .entity("No security roles are allowed to invoke the specified method.").build());
	}

}
