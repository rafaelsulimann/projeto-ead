package com.ead.course.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

public class ModuleDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    public ModuleDto(){
    }

    public ModuleDto(String title, String description) {
        this.title = title;
        this.description = description;
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

    @Override
    public String toString() {
        return "ModuleDto [description=" + description + ", title=" + title + "]";
    }      
    
}
