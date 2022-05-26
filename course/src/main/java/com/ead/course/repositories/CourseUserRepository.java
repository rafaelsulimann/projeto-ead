package com.ead.course.repositories;

import java.util.UUID;

import com.ead.course.models.CourseModel;
import com.ead.course.models.CourseUserModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseUserRepository extends JpaRepository<CourseUserModel, UUID> {

    boolean existsByCourseAndUserId(CourseModel courseModel, UUID userId);
    
}
