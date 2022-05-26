package com.ead.course.controllers.exceptions;

import java.time.LocalDateTime;
import java.time.ZoneId;

import javax.servlet.http.HttpServletRequest;

import com.ead.course.services.exceptions.CourseNotFoundException;
import com.ead.course.services.exceptions.ExistsByCourseAndUserIdException;
import com.ead.course.services.exceptions.LessonNotFoundException;
import com.ead.course.services.exceptions.ModuleNotFoundException;
import com.ead.course.services.exceptions.UserIsBlockedException;
import com.ead.course.services.exceptions.UserNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CourseResourceExceptionHandler {

    @ExceptionHandler(CourseNotFoundException.class)
    public ResponseEntity<StandardError> courseResourceNotFound(CourseNotFoundException e, HttpServletRequest request){
        String error = "Curso não encontrado";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(LocalDateTime.now(ZoneId.of("UTC")), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(ModuleNotFoundException.class)
    public ResponseEntity<StandardError> moduleResourceNotFound(ModuleNotFoundException e, HttpServletRequest request){
        String error = "Módulo não encontrado";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(LocalDateTime.now(ZoneId.of("UTC")), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(LessonNotFoundException.class)
    public ResponseEntity<StandardError> lessonResourceNotFound(LessonNotFoundException e, HttpServletRequest request){
        String error = "Módulo não encontrado";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(LocalDateTime.now(ZoneId.of("UTC")), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<StandardError> userResourceNotFound(UserNotFoundException e, HttpServletRequest request){
        String error = "Usuário não encontrado";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(LocalDateTime.now(ZoneId.of("UTC")), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(ExistsByCourseAndUserIdException.class)
    public ResponseEntity<StandardError> existsByCourseAndUserIdException(ExistsByCourseAndUserIdException e, HttpServletRequest request){
        String error = "Exists by Course and UserId";
        HttpStatus status = HttpStatus.CONFLICT;
        StandardError err = new StandardError(LocalDateTime.now(ZoneId.of("UTC")), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(UserIsBlockedException.class)
    public ResponseEntity<StandardError> userIsBlockedException(UserIsBlockedException e, HttpServletRequest request){
        String error = "User is blocked";
        HttpStatus status = HttpStatus.CONFLICT;
        StandardError err = new StandardError(LocalDateTime.now(ZoneId.of("UTC")), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
}