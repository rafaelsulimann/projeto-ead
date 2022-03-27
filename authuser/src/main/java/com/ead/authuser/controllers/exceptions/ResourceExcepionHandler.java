package com.ead.authuser.controllers.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import com.ead.authuser.services.exceptions.NotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExcepionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound(NotFoundException e, HttpServletRequest request){
        String error = "Resource not found";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
    
}
