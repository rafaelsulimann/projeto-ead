package com.ead.authuser.services.exceptions;

public class NotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public NotFoundException(Object id){
        super("Objeto não encontrado. id " + id);
    }
    
}
