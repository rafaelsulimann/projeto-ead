package com.ead.course.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

public class LessonDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank
    private String title;
    
    private String description;

    @NotBlank
    private String videoUrl;

    public LessonDto(){
    }

    public LessonDto(@NotBlank String title, String description, @NotBlank String videoUrl) {
        this.title = title;
        this.description = description;
        this.videoUrl = videoUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    
    
}
