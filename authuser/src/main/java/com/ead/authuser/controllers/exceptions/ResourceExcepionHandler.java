package com.ead.authuser.controllers.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import com.ead.authuser.services.exceptions.ExistsByEmailException;
import com.ead.authuser.services.exceptions.ExistsByUserNameException;
import com.ead.authuser.services.exceptions.NotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExcepionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound(NotFoundException e, HttpServletRequest request){
        String error = "Objeto não encontrado";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(ExistsByUserNameException.class)
    public ResponseEntity<StandardError> existsByUserNameException(ExistsByUserNameException e, HttpServletRequest request){
        String error = "Exists by username";
        HttpStatus status = HttpStatus.CONFLICT;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(ExistsByEmailException.class)
    public ResponseEntity<StandardError> existsByEmailException(ExistsByEmailException e, HttpServletRequest request){
        String error = "Exists by email";
        HttpStatus status = HttpStatus.CONFLICT;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

}

    

