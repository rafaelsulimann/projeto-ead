package com.ead.course.controllers;

import java.net.URI;
import java.util.UUID;

import javax.validation.Valid;

import com.ead.course.clients.AuthUserClient;
import com.ead.course.dtos.SubscriptionDto;
import com.ead.course.dtos.UserDto;
import com.ead.course.models.CourseModel;
import com.ead.course.models.CourseUserModel;
import com.ead.course.models.enums.UserStatus;
import com.ead.course.services.CourseService;
import com.ead.course.services.CourseUserService;
import com.ead.course.services.exceptions.ExistsByCourseAndUserIdException;
import com.ead.course.services.exceptions.UserIsBlockedException;
import com.ead.course.services.exceptions.UserNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class CourseUserController {

    @Autowired
    private AuthUserClient courseClient;

    @Autowired
    private CourseService courseService;

    @Autowired
    private CourseUserService courseUserService;

    @GetMapping(value = "/courses/{courseId}/users")
    public ResponseEntity<Page<UserDto>> findAllUsersByUser(@PathVariable UUID courseId,
            @PageableDefault(page = 0, size = 10, sort = "userId", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok().body(courseClient.findAllUsersByCourse(courseId, pageable));
    }

    @PostMapping(value = "/courses/{courseId}/users/subscription")
    public ResponseEntity<CourseUserModel> saveSubscriptionUserInCourse(@PathVariable UUID courseId, @RequestBody @Valid SubscriptionDto subscriptionDto){
        ResponseEntity<UserDto> responseUser;
        CourseModel course = courseService.findById(courseId);
        if(courseUserService.existsByCourseAndUserId(course, subscriptionDto.getUserId())){
            log.warn("User {} já cadastrado neste curso", subscriptionDto.getUserId());
            throw new ExistsByCourseAndUserIdException(subscriptionDto.getUserId());
        }
        try{   
            responseUser =  courseClient.findOneUserById(subscriptionDto.getUserId()); 
            if(responseUser.getBody().getUserStatus().equals(UserStatus.BLOCKED)){
                log.warn("User {} está bloqueado");
                throw new UserIsBlockedException(subscriptionDto.getUserId());
            }
        }
        catch(HttpStatusCodeException e){
            if(e.getStatusCode().equals(HttpStatus.NOT_FOUND)){
                throw new UserNotFoundException(subscriptionDto.getUserId());
            }
        }   
        CourseUserModel courseUserModel = courseUserService.saveAndSendSubscriptionUserInCourse(course.convertToCourseUserModel(subscriptionDto.getUserId()));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{courseId}").buildAndExpand(courseUserModel.getCourseUserId()).toUri();
        return ResponseEntity.created(uri).body(courseUserModel);
    }

}
