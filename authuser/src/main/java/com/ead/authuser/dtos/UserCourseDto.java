package com.ead.authuser.dtos;

import java.io.Serializable;
import java.util.UUID;

import javax.validation.constraints.NotNull;

public class UserCourseDto implements Serializable{

    private static final long serialVersionUID = 1L;

    private UUID userId;

    @NotNull
    private UUID courseId;

    public UserCourseDto(){
    }    

    public UserCourseDto(UUID userId, @NotNull UUID courseId) {
        this.userId = userId;
        this.courseId = courseId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getCourseId() {
        return courseId;
    }

    public void setCourseId(UUID courseId) {
        this.courseId = courseId;
    }    
    
}
