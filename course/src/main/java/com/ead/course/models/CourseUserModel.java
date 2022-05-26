package com.ead.course.models;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_courses_users")
public class CourseUserModel implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID courseUserId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "course_id")
    private CourseModel course;

    @Column(nullable = false)
    private UUID userId;

    public CourseUserModel(){
    }

    public CourseUserModel(UUID courseUserId, CourseModel course, UUID userId) {
        this.courseUserId = courseUserId;
        this.course = course;
        this.userId = userId;
    }

    public UUID getCourseUserId() {
        return courseUserId;
    }

    public void setCourseUserId(UUID courseUserId) {
        this.courseUserId = courseUserId;
    }

    public CourseModel getCourse() {
        return course;
    }

    public void setCourse(CourseModel course) {
        this.course = course;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }    
    
}
