package com.ead.authuser.repositories;

import java.util.UUID;

import com.ead.authuser.models.UserCourseModel;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCourseRepository extends JpaRepository<UserCourseModel, UUID>{
    
}
