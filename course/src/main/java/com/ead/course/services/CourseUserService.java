package com.ead.course.services;

import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ead.course.clients.AuthUserClient;
import com.ead.course.models.CourseModel;
import com.ead.course.models.CourseUserModel;
import com.ead.course.repositories.CourseUserRepository;

@Service
public class CourseUserService {
    
    @Autowired
    private CourseUserRepository courseUserRepository;

    @Autowired
    private AuthUserClient authUserClient;

    public CourseUserModel save(CourseUserModel obj){
        return courseUserRepository.save(obj);
    }

    @Transactional
    public CourseUserModel saveAndSendSubscriptionUserInCourse(CourseUserModel courseUserModel){
        courseUserModel = courseUserRepository.save(courseUserModel);
        authUserClient.postSubscriptionUserInCourse(courseUserModel.getCourse().getCourseId(), courseUserModel.getUserId());
        return courseUserModel;
    }
    
    public boolean existsByCourseAndUserId(CourseModel courseModel, UUID userId){
        return courseUserRepository.existsByCourseAndUserId(courseModel, userId);
    }

    public boolean existsByUserId(UUID userId){
        return courseUserRepository.existsByUserId(userId);
    }

    @Transactional
    public void deleteCourseUserByUser(UUID userId){
        courseUserRepository.deleteAllByUserId(userId);
    }
}
