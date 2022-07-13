package com.ead.course.dtos;

import java.util.UUID;

public class NotificationCommandDto {

    private String title;
    private String message;
    private UUID userId;

    public NotificationCommandDto(){
    }

    public NotificationCommandDto(String title, String message, UUID userId) {
        this.title = title;
        this.message = message;
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }    
    
}
