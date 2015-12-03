package com.umi.common.action;

import java.io.IOException;

import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response.Status;

import org.glassfish.jersey.internal.util.Base64;

import com.umi.common.services.facebook.FacebookSignedRequest;
import com.umi.common.utils.CustomException;
import com.umi.common.utils.StringUtil;
import com.google.gson.Gson;

import lombok.extern.java.Log;

@Path("/facebookfriends")
@Log
@PermitAll
public class FacebookServlet {
	@Context HttpServletRequest request;
	@Context HttpServletResponse response;
	
	@POST
	public void post( @DefaultValue("") @QueryParam("signed_request") String signed_request ) throws IOException {
		log.info("Start view");
		
		FacebookSignedRequest FacebookSR = null;
		
		
		 
		 try {
			 	String payload = signed_request.split("[.]", 2)[1];
			 	payload = payload.replace("-", "+").replace("_", "/").trim();
	            byte[] decoded = Base64.decode(payload.getBytes( "UTF8" ));
	            String data = new String(decoded, "UTF8");
	            FacebookSR =  new Gson().fromJson(data, FacebookSignedRequest.class);
	  
	        } catch(Exception e) {
	            log.severe(StringUtil.exceptionFormat(e));
	            throw new CustomException(Status.BAD_REQUEST,  "No data to save.");
	        }
		
		String oauthToken = FacebookSR.getOauth_token();
		
		if(oauthToken == null) {
/*
		     response.setContentType("text/html");
		     String authURL = "https://www.Facebook.com/dialog/oauth?client_id="
		       + API_KEY
		       + "&redirect_uri=https://apps.Facebook.com/myoldfriends/&scope=";
		     writer.print("<script> top.location.href='" + authURL + "'</script>");
		     writer.close();*/

		}
	}
}
