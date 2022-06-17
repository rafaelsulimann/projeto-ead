package com.ead.authuser.controllers;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ead.authuser.dtos.InstructorDto;
import com.ead.authuser.models.UserModel;
import com.ead.authuser.models.enums.UserType;
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
    public ResponseEntity<Object> saveSubscriptionInstructor(@RequestBody @Valid InstructorDto instructorDto) {
        log.debug("POST saveSubscriptionInstructor instructorDto received {} ", instructorDto.toString());
        Optional<UserModel> userModelOptional = userService.findById(instructorDto.getUserId());
        if (!userModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        } else {
            var userModel = userModelOptional.get();
            userModel.setUserType(UserType.INSTRUCTOR);
            userModel.setLastUpdateTime(LocalDateTime.now(ZoneId.of("UTC")));
            userService.save(userModel);
            log.debug("POST saveSubscriptionInstructor userId saved {} ", userModel.getUserId());
            log.info("User saved successfully userId {} ", userModel.getUserId());
            return ResponseEntity.ok().body(userModel);            
        }

    }

}
