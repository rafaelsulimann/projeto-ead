package com.ead.course.services;

import java.util.UUID;

import com.ead.course.models.CourseModel;
import com.ead.course.models.CourseUserModel;
import com.ead.course.repositories.CourseUserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseUserService {
    
    @Autowired
    private CourseUserRepository courseUserRepository;

    public CourseUserModel save(CourseUserModel obj){
        return courseUserRepository.save(obj);
    }
    
    public boolean existsByCourseAndUserId(CourseModel courseModel, UUID userId){
        return courseUserRepository.existsByCourseAndUserId(courseModel, userId);
    }
}
