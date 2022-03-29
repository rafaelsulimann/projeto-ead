package com.ead.authuser.services.exceptions;

public class ExistsByEmailException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ExistsByEmailException(String email){
        super("Já existe um usuário com este email. Email: " + email);
    }
    
}
