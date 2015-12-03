package com.umi.common.filter;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MediaType;

public class CharsetResponseFilter  implements ContainerResponseFilter {
 
    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
        throws IOException {
    	  MediaType contentType = responseContext.getMediaType();
    	  if(contentType == null){
    		  contentType = MediaType.TEXT_HTML_TYPE;
    	  }
    	  responseContext.getHeaders().putSingle("Content-Type", contentType.toString() + ";charset=UTF-8");
          responseContext.getHeaders().add("X-Powered-By", "Umitay Turdykulov");
    }
}
 