package com.ead.authuser.models;

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
@Table(name = "tb_users_courses")
public class UserCourseModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID userCourseId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    private UserModel userModel;

    @Column(nullable = false)
    private UUID courseId;

    public UserCourseModel(){
    }

    public UserCourseModel(UUID userCourseId, UserModel userModel, UUID courseId) {
        this.userCourseId = userCourseId;
        this.userModel = userModel;
        this.courseId = courseId;
    }

    public UUID getUserCourseId() {
        return userCourseId;
    }

    public void setUserCourseId(UUID userCourseId) {
        this.userCourseId = userCourseId;
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    public UUID getCourseId() {
        return courseId;
    }

    public void setCourseId(UUID courseId) {
        this.courseId = courseId;
    }    
    
}
