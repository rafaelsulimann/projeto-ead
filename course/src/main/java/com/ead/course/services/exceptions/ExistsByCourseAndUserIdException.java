package com.ead.course.services.exceptions;

import java.util.UUID;

public class ExistsByCourseAndUserIdException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;

    public ExistsByCourseAndUserIdException(UUID userId){
        super("Usuário " + userId + " já possui este curso");
    }
    
}

