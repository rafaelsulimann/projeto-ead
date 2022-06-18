package com.ead.authuser.dtos;

import java.io.Serializable;
import java.util.UUID;

import com.ead.authuser.enums.CourseLevel;
import com.ead.authuser.enums.CourseStatus;

public class CourseDto implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private UUID courseId;
    private String name;
    private String description;
    private String imgUrl;
    private CourseStatus courseStatus;
    private CourseLevel courseLevel;
    private UUID userInstructor;

    public CourseDto(){
    }

    public CourseDto(UUID courseId, String name, String description, String imgUrl, CourseStatus courseStatus,
            CourseLevel courseLevel, UUID userInstructor) {
        this.courseId = courseId;
        this.name = name;
        this.description = description;
        this.imgUrl = imgUrl;
        this.courseStatus = courseStatus;
        this.courseLevel = courseLevel;
        this.userInstructor = userInstructor;
    }

    public UUID getCourseId() {
        return courseId;
    }

    public void setCourseId(UUID courseId) {
        this.courseId = courseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public CourseStatus getCourseStatus() {
        return courseStatus;
    }

    public void setCourseStatus(CourseStatus courseStatus) {
        this.courseStatus = courseStatus;
    }

    public CourseLevel getCourseLevel() {
        return courseLevel;
    }

    public void setCourseLevel(CourseLevel courseLevel) {
        this.courseLevel = courseLevel;
    }

    public UUID getUserInstructor() {
        return userInstructor;
    }

    public void setUserInstructor(UUID userInstructor) {
        this.userInstructor = userInstructor;
    }

    
}
