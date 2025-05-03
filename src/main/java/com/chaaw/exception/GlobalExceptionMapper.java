package com.chaaw.exception;

import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class GlobalExceptionMapper implements ExceptionMapper<Throwable> {
    
    @Context
    UriInfo uriInfo;
    
    @Override
    public Response toResponse(Throwable exception) {
        if (exception instanceof NotFoundException) {
            return buildErrorResponse(exception.getMessage(), Response.Status.NOT_FOUND);
        }
        
        if (exception instanceof ConstraintViolationException) {
            return buildErrorResponse("Validation failed: " + exception.getMessage(), 
                                    Response.Status.BAD_REQUEST);
        }
        
        if (exception instanceof IllegalArgumentException) {
            return buildErrorResponse(exception.getMessage(), Response.Status.BAD_REQUEST);
        }
        
        // Log the exception for internal server errors
        exception.printStackTrace();
        return buildErrorResponse("Internal server error", 
                                Response.Status.INTERNAL_SERVER_ERROR);
    }
    
    private Response buildErrorResponse(String message, Response.Status status) {
        ErrorResponse errorResponse = new ErrorResponse(
            message,
            status.getStatusCode(),
            uriInfo.getPath()
        );
        
        return Response.status(status)
                      .entity(errorResponse)
                      .build();
    }
}