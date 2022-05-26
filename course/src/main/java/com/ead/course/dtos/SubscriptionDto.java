package com.ead.course.dtos;

import java.io.Serializable;
import java.util.UUID;

import javax.validation.constraints.NotNull;

public class SubscriptionDto implements Serializable{

    private static final long serialVersionUID = 1L;

    @NotNull
    private UUID userId;

    public SubscriptionDto(){
    }

    public SubscriptionDto(UUID userId){
        this.userId = userId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }    
    
}
