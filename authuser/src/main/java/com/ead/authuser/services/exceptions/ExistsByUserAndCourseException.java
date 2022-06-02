package com.ead.authuser.services.exceptions;

import java.util.UUID;

public class ExistsByUserAndCourseException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public ExistsByUserAndCourseException(UUID userId){
        super("Usuário " + userId + " já possui este curso");
    }
    
}
