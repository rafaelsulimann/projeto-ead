package com.ead.course.services.exceptions;

public class LessonNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public LessonNotFoundException(Object id){
        super("Aula não encontrada. id: " + id);
    }
    
}
