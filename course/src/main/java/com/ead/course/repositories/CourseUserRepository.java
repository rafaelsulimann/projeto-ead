package com.ead.course.repositories;

import java.util.UUID;

import com.ead.course.models.CourseUserModel;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseUserRepository extends JpaRepository<CourseUserModel, UUID> {
    
}
