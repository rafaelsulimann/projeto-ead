package com.ead.course.models;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_courses_users")
public class CourseUserModel implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID courseUserId;

    @Column(name = "course_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private CourseModel courseModel;

    @Column(nullable = false)
    private UUID userId;

    public CourseUserModel(){
    }

    public CourseUserModel(UUID courseUserId, CourseModel courseModel, UUID userId) {
        this.courseUserId = courseUserId;
        this.courseModel = courseModel;
        this.userId = userId;
    }

    public UUID getCourseUserId() {
        return courseUserId;
    }

    public void setCourseUserId(UUID courseUserId) {
        this.courseUserId = courseUserId;
    }

    public CourseModel getCourseModel() {
        return courseModel;
    }

    public void setCourseModel(CourseModel courseModel) {
        this.courseModel = courseModel;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }    
    
}
