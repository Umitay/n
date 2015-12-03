package com.umi.common.filter;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Priority;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;

import lombok.extern.java.Log;

import com.umi.common.data.persist.EnvironmentConfig;
import com.umi.common.utils.EncodingUtil;

@PreMatching
@Provider
@Priority(Priorities.AUTHENTICATION)
@Log
public class SecurityRequestFilter implements ContainerRequestFilter {
		private RolesAllowed roles_allowed;
		@Context UriInfo uriInfo;
		
		public SecurityRequestFilter(RolesAllowed ra) {
			this.roles_allowed = ra;
		}

		@Override
		public void filter(ContainerRequestContext requestContext ) throws IOException {
			log.info("Start SecurityRequestFilter.filter");
			Boolean is_allowed = false;
			String currentPerson = null;
			List<String> raList = Arrays.asList(roles_allowed.value());
			
			//if( raList.contains("ADMIN") || raList.contains("SEO") ){
				Map<String, Cookie> cookies = requestContext.getCookies();
				for (Entry<String, Cookie> c : cookies.entrySet()) {
					log.info("Entry: " +  c.getKey() );
					Cookie cooke = c.getValue();
					log.info("domain: " + cooke.getDomain()+" name: "+cooke.getName() + " value: "+cooke.getValue() );
				}
				Cookie cookie = requestContext.getCookies().get("p1");
				 
				if( cookie != null ){
				 currentPerson = cookie.getValue();
				 log.info("currentPerson: "+currentPerson + "MD5: "+EncodingUtil.MD5("offer" + EnvironmentConfig.SECRET_KEY));
				 if( currentPerson != null && EncodingUtil.MD5("offer" + EnvironmentConfig.SECRET_KEY).equals(currentPerson) ){
						is_allowed = true;
						 log.info("is_allowed: "+is_allowed);
					}
				 }
				
		//	}
			
			if (!is_allowed) {
				URI uri = null;
				try {
					uri = new URI("/login");
				} catch (URISyntaxException e) {
					log.severe( e.getMessage() );
				}
				requestContext.abortWith(Response.temporaryRedirect(uri).build());
					
			}
			log.info("End SecurityRequestFilter.filter");
		}
		
	}
