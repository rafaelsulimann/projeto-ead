package com.ead.authuser.dtos;

import java.io.Serializable;
import java.util.UUID;

import javax.validation.constraints.NotNull;

public class InstructorDto implements Serializable{

    private static final long serialVersionUID = 1L;

    @NotNull
    private UUID userId;

    public InstructorDto(){
    }

    public InstructorDto(UUID userId){
        this.userId = userId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }    
    
}
