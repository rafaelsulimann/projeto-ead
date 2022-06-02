package com.ead.course.dtos;

import java.io.Serializable;
import java.util.UUID;

public class CourseUserDto implements Serializable{

    private static final long serialVersionUID = 1L;

    private UUID userId;
    private UUID courseId;

    public CourseUserDto(){
    }

    public CourseUserDto(UUID userId, UUID courseId) {
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
