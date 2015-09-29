package com.umi.healthy.filter;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;

import lombok.extern.java.Log;

import org.glassfish.jersey.message.internal.MediaTypes;
import org.glassfish.jersey.server.ContainerRequest;
@Log
public class InputFilter implements ContainerRequestFilter{

	@Override
	public void filter(ContainerRequestContext requestContext)
			throws IOException {
		/*switch ( requestContext.getMethod() ) {
		case "POST":
			ContainerRequest request = (ContainerRequest) requestContext;
			 
			if ( requestContext.hasEntity()
		          && MediaTypes.typeEqual(MediaType.APPLICATION_FORM_URLENCODED_TYPE, request.getMediaType())) {
		                request.bufferEntity();
		                Form  form = request.readEntity(Form.class);
		     }
								
			
			log.info("Post: ");
			break;
		}*/
		
	}

	
	
}
