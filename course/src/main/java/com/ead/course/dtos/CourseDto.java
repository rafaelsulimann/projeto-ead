package com.ead.course.dtos;

import java.io.Serializable;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.ead.course.models.enums.CourseLevel;
import com.ead.course.models.enums.CourseStatus;

public class CourseDto implements Serializable{

    private static final long serialVersionUID = 1L;

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    private String imgUrl;

    @NotNull
    private CourseStatus courseStatus;

    @NotNull
    private CourseLevel courseLevel;

    @NotNull
    private UUID userInstructor;
    
    public CourseDto(){
    }

    public CourseDto(String name, String description, String imgUrl, CourseStatus courseStatus, CourseLevel courseLevel,
            UUID userInstructor) {
        this.name = name;
        this.description = description;
        this.imgUrl = imgUrl;
        this.courseStatus = courseStatus;
        this.courseLevel = courseLevel;
        this.userInstructor = userInstructor;
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

    @Override
    public String toString() {
        return "CourseDto [courseLevel=" + courseLevel + ", courseStatus=" + courseStatus + ", description="
                + description + ", imgUrl=" + imgUrl + ", name=" + name + ", userInstructor=" + userInstructor + "]";
    }    
    
}
