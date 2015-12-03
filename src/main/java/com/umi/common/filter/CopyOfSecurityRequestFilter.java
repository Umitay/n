/*package com.umi.common.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Priority;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;

import lombok.extern.java.Log;

import org.glassfish.jersey.message.internal.MediaTypes;
import org.glassfish.jersey.server.ContainerRequest;

import com.umi.common.utils.NetworkUtils;

@Provider
@Priority(Priorities.AUTHENTICATION)
@Log
public class CopyOfSecurityRequestFilter implements ContainerRequestFilter {
		private RolesAllowed roles_allowed;
		
		public CopyOfSecurityRequestFilter(RolesAllowed ra) {
			this.roles_allowed = ra;
		}

		@Override
		public void filter(ContainerRequestContext requestContext ) throws IOException {
			log.info("Start SecurityRequestFilter.filter");
			
			Boolean is_allowed = false;
			String hashQuery = "";
			
			List<String> raList = Arrays.asList(roles_allowed.value());
			
			if( raList.contains("API") ){
				switch ( requestContext.getMethod() ) {
					case "GET":
						hashQuery = requestContext.getUriInfo().getRequestUri().getQuery();
						log.info("hashQuery: "+hashQuery);
						break;
					case "POST":
						ContainerRequest request = (ContainerRequest) requestContext;
						 
						if ( requestContext.hasEntity()
					          && MediaTypes.typeEqual(MediaType.APPLICATIOFORM_URLENCODED_TYPE, request.getMediaType())) {
					                request.bufferEntity();
					                hashQuery = request.readEntity(String.class);
					     }
											
						
						log.info("Post: "+hashQuery);
						break;
				}
				
				if( NetworkUtils.validateHashQuery(hashQuery) == null){
					is_allowed = true;
				}
			}
			
			if (!is_allowed) {
				  requestContext.abortWith(
			              Response.status(Status.UNAUTHORIZED)
			              .header(HttpHeaders.WWW_AUTHENTICATE, "Basic realm=\"403\"")
			              .entity("You don't have permission to access / on this server..").build());
			    }
			
			log.info("End SecurityRequestFilter.filter");
		}
		
	}
*/