package com.umi.common.filter;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;

import lombok.extern.java.Log;
@Log
public class InputFilter implements ContainerRequestFilter{

	@Override
	public void filter(ContainerRequestContext requestContext)
			throws IOException {
		/*switch ( requestContext.getMethod() ) {
		case "POST":
			ContainerRequest request = (ContainerRequest) requestContext;
			 
			if ( requestContext.hasEntity()
		          && MediaTypes.typeEqual(MediaType.APPLICATIOFORM_URLENCODED_TYPE, request.getMediaType())) {
		                request.bufferEntity();
		                Form  form = request.readEntity(Form.class);
		     }
								
			
			log.info("Post: ");
			break;
		}*/
		
	}

	
	
}
