package com.ead.authuser.validation.impl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.ead.authuser.validation.UsernameConstraint;

public class UsernameConstraintImpl implements ConstraintValidator<UsernameConstraint, String> {

    @Override
    public void initialize(UsernameConstraint constraintAnnotation){
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String userName, ConstraintValidatorContext context) {
        if(userName == null || userName.trim().isEmpty() || userName.contains(" ")){
            return false;
        }
        return true;
    }
    
}
