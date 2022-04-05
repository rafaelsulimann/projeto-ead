package com.ead.course.services.exceptions;

public class ModuleNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ModuleNotFoundException(Object id){
        super("Módulo não encontrado para este curso. id: " + id);
    }
    
}
