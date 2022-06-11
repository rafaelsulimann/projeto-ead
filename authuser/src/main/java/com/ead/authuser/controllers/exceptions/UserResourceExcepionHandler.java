package com.ead.authuser.controllers.exceptions;

import java.time.LocalDateTime;
import java.time.ZoneId;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ead.authuser.services.exceptions.CourseNotFoundException;
import com.ead.authuser.services.exceptions.ExistsByCpfException;
import com.ead.authuser.services.exceptions.ExistsByEmailException;
import com.ead.authuser.services.exceptions.ExistsByUserAndCourseException;
import com.ead.authuser.services.exceptions.ExistsByUserNameException;
import com.ead.authuser.services.exceptions.PasswordException;
import com.ead.authuser.services.exceptions.UserNotFoundException;

@ControllerAdvice
public class UserResourceExcepionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<StandardError> userResourceNotFound(UserNotFoundException e, HttpServletRequest request){
        String error = "Usuário não encontrado";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(LocalDateTime.now(ZoneId.of("UTC")), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(ExistsByUserNameException.class)
    public ResponseEntity<StandardError> existsByUserNameException(ExistsByUserNameException e, HttpServletRequest request){
        String error = "Username existente";
        HttpStatus status = HttpStatus.CONFLICT;
        StandardError err = new StandardError(LocalDateTime.now(ZoneId.of("UTC")), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(ExistsByEmailException.class)
    public ResponseEntity<StandardError> existsByEmailException(ExistsByEmailException e, HttpServletRequest request){
        String error = "Email existente";
        HttpStatus status = HttpStatus.CONFLICT;
        StandardError err = new StandardError(LocalDateTime.now(ZoneId.of("UTC")), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(ExistsByCpfException.class)
    public ResponseEntity<StandardError> existsByCpfException(ExistsByCpfException e, HttpServletRequest request){
        String error = "CPF existente";
        HttpStatus status = HttpStatus.CONFLICT;
        StandardError err = new StandardError(LocalDateTime.now(ZoneId.of("UTC")), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(PasswordException.class)
    public ResponseEntity<StandardError> passwordException(PasswordException e, HttpServletRequest request){
        String error = "Password inválido";
        HttpStatus status = HttpStatus.CONFLICT;
        StandardError err = new StandardError(LocalDateTime.now(ZoneId.of("UTC")), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(ExistsByUserAndCourseException.class)
    public ResponseEntity<StandardError> existsByUserAndCourseException(ExistsByUserAndCourseException e, HttpServletRequest request){
        String error = "Exists By User and Course id";
        HttpStatus status = HttpStatus.CONFLICT;
        StandardError err = new StandardError(LocalDateTime.now(ZoneId.of("UTC")), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
    @ExceptionHandler(CourseNotFoundException.class)
    public ResponseEntity<StandardError> courseNotFoundException(CourseNotFoundException e, HttpServletRequest request){
        String error = "Curso não encontrado";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(LocalDateTime.now(ZoneId.of("UTC")), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }


}

    

