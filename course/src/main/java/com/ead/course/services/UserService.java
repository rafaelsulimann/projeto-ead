package com.ead.course.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.ead.course.models.UserModel;
import com.ead.course.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Page<UserModel> findAll(Specification<UserModel> spec, Pageable pageable){
        return userRepository.findAll(spec, pageable);
    }
    
}
