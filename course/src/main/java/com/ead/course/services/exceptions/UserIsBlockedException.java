package com.ead.course.services.exceptions;

import java.util.UUID;

public class UserIsBlockedException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public UserIsBlockedException(UUID userId){
        super("Usuário está bloqueado. id: " + userId);
    }
    
}
