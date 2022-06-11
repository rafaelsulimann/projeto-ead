package com.ead.authuser.services;

import java.util.UUID;

import javax.transaction.Transactional;

import com.ead.authuser.models.UserCourseModel;
import com.ead.authuser.models.UserModel;
import com.ead.authuser.repositories.UserCourseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCourseService {

    @Autowired
    private UserCourseRepository userCourseRepository;

    public UserCourseModel save(UserCourseModel obj){
        return userCourseRepository.save(obj);
    }

    public boolean existsByUserAndCourseId(UserModel obj, UUID courseId){
        return userCourseRepository.existsByUserModelAndCourseId(obj, courseId);
    }

    public boolean existsByCourseId(UUID courseId){
        return userCourseRepository.existsByCourseId(courseId);
    }

    @Transactional
    public void deleteUserCourseByCourse(UUID courseId){
        userCourseRepository.deleteAllByCourseId(courseId);
    }
    
}
