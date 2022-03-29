package com.ead.authuser.services.exceptions;

public class ExistsByUserNameException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ExistsByUserNameException(String userName){
        super("Já existe um usuário com este username. Username: " + userName);
    }
    
}
