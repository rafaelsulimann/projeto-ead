package com.ead.authuser.services.exceptions;

public class ExistsByCpfException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ExistsByCpfException(String cpf){
        super("Já existe um usuário com este CPF. CPF: " + cpf);
    }
    
}
