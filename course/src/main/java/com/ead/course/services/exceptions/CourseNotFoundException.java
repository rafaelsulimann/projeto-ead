package com.ead.course.services.exceptions;

public class CourseNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public CourseNotFoundException(Object id){
        super("Curso não encontrado. id " + id);
    }
    
}
