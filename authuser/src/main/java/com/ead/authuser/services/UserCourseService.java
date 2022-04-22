package com.ead.authuser.services;

import com.ead.authuser.repositories.UserCourseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCourseService {

    @Autowired
    private UserCourseRepository userCourseRepository;
    
}
