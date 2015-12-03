package com.umi.common.utils;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.Response.Status.Family;
import javax.ws.rs.core.Response.StatusType;

import lombok.extern.java.Log;
/**
 * Follow exceptions can be thrown from anywhere while processing
 * BadRequestException
 * NotAuthorizedException
 * ForbiddenException
 * NotFoundException
 * NotAllowedException
 * NotAcceptableException
 * NotSupportedException
 * InternalServerErrorException
 * ServiceUnavailableException
 * WebApplicationException
 * Usage : throw new CustomException(Status.BAD_REQUEST, "Field 'name' is missing.");
 */
@Log
public class CustomException extends WebApplicationException  {

	private static final long serialVersionUID = 6127694954021192708L;
	public static class CustomStatus implements StatusType {
        final int statusCode;
        final String reasonPhrase;

        public CustomStatus(int statusCode, String reasonPhrase) {
            this.statusCode = statusCode;
            this.reasonPhrase = reasonPhrase;
        }

        @Override
        public int getStatusCode() {
            return statusCode;
        }
        @Override
        public Family getFamily() {
            return Family.familyOf(statusCode);
        }
        @Override
        public String getReasonPhrase() {
            return reasonPhrase;
        }
     }

	 public CustomException() { }

	 public CustomException(int status) {
	        super(status);
	 }
	 
	 public CustomException(Status status, String message) {
	        this(status.getStatusCode(), message);
	 }       
	 
	 public CustomException( int status, String message ) {
	        super(message, Response.status(new CustomStatus( status, message )). build());
	        
	        if( status == Status.INTERNAL_SERVER_ERROR.getStatusCode() ){
	        	log.severe( status+ ", "+ message );
	        }else{
	        	log.warning( status+ ", "+ message );
	        }
	 }
	 
	 public CustomException(String message) {
	        this(Status.INTERNAL_SERVER_ERROR, message);
	 }
}
