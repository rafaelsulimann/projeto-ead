package com.ead.course.services;

import com.ead.course.repositories.ModuleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModuleService {

    @Autowired
    private ModuleRepository repository;
    
}
