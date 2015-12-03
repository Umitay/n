package com.umi.common.provider;

import javax.annotation.security.DenyAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.container.DynamicFeature;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.ext.Provider;

import lombok.extern.java.Log;

import com.umi.common.filter.DenyAllFilter;
import com.umi.common.filter.SecurityRequestFilter;
@Provider
@Log
public class RolesAllowedDynamicFeature implements DynamicFeature {
	
	@Override
	public void configure(ResourceInfo resourceInfo, FeatureContext context) {
		//log.info("Start RolesAllowedDynamicFeature.configure");
		
		if (resourceInfo.getResourceMethod().isAnnotationPresent(DenyAll.class)) {
			context.register(DenyAllFilter.class);
		}
		
		RolesAllowed ra= resourceInfo.getResourceMethod().getAnnotation(RolesAllowed.class);
		
		if (ra != null) {
			//log.info( resourceInfo.getResourceMethod() + " has Roles Allowed  ");
			SecurityRequestFilter sFilter = new SecurityRequestFilter(ra);
			context.register(sFilter);
		}
		
		//log.info("End  RolesAllowedDynamicFeature.configure");
	}

}
