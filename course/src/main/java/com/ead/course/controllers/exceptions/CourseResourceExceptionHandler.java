package com.ead.course.controllers.exceptions;

import java.time.LocalDateTime;
import java.time.ZoneId;

import javax.servlet.http.HttpServletRequest;

import com.ead.course.services.exceptions.CourseNotFoundException;
import com.ead.course.services.exceptions.LessonNotFoundException;
import com.ead.course.services.exceptions.ModuleNotFoundException;

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
}