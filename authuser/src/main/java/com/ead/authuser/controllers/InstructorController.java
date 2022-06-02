package com.ead.authuser.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ead.authuser.dtos.InstructorDto;
import com.ead.authuser.models.UserModel;
import com.ead.authuser.services.UserService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/instructors")
public class InstructorController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/subscription")
    public ResponseEntity<UserModel> saveSubscriptionInstructor(@RequestBody @Valid InstructorDto instructorDto){
        log.debug("POST saveSubscriptionInstructor instructorDto received {} ", instructorDto.toString());
        UserModel obj = userService.findById(instructorDto.getUserId());
        obj = userService.insertInstructor(obj);
        obj = userService.save(obj);
        log.debug("POST saveSubscriptionInstructor userId saved {} ", obj.getUserId());
        log.info("User saved successfully userId {} ", obj.getUserId());
        return ResponseEntity.ok().body(obj);
    }
    
}
