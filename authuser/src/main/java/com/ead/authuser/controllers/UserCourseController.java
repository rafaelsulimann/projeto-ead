package com.ead.authuser.controllers;

import java.net.URI;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ead.authuser.clients.CourseClient;
import com.ead.authuser.dtos.CourseDto;
import com.ead.authuser.dtos.UserCourseDto;
import com.ead.authuser.models.UserCourseModel;
import com.ead.authuser.models.UserModel;
import com.ead.authuser.services.UserCourseService;
import com.ead.authuser.services.UserService;
import com.ead.authuser.services.exceptions.CourseNotFoundException;
import com.ead.authuser.services.exceptions.ExistsByUserAndCourseException;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserCourseController {

    @Autowired
    private CourseClient userClient;

    @Autowired
    private UserService userService;

    @Autowired
    private UserCourseService userCourseService;

    @GetMapping(value = "/users/{userId}/courses")
    public ResponseEntity<Page<CourseDto>> findAllCourseByUsers(
                                    @PathVariable UUID userId,
                                    @PageableDefault(page = 0, size = 10, sort = "courseId", direction = Sort.Direction.ASC) Pageable pageable) {
        userService.findById(userId);
        return ResponseEntity.ok().body(userClient.findAllCoursesByUser(userId, pageable));
    }

    @PostMapping(value = "/users/{userId}/courses/subscription")
    public ResponseEntity<UserCourseModel> saveSubscriptionUserInCourse(@PathVariable UUID userId, @RequestBody @Valid UserCourseDto userCourseDto){
        UserModel obj = userService.findById(userId);
        if(userCourseService.existsByUserAndCourseId(obj, userCourseDto.getCourseId())){
            throw new ExistsByUserAndCourseException(userId);
        }
        UserCourseModel userCourseModel = userCourseService.save(obj.convertToUserCourseModel(userCourseDto.getCourseId()));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{userCourseId}").buildAndExpand(userCourseModel.getUserCourseId()).toUri();
        return ResponseEntity.created(uri).body(userCourseModel);
    }

    @DeleteMapping(value = "/users/courses/{courseId}")
    public ResponseEntity<Object> deleteUserCourseByCourse(@PathVariable UUID courseId){
        if(!userCourseService.existsByCourseId(courseId)){
            throw new CourseNotFoundException(courseId);
        }
        userCourseService.deleteUserCourseByCourse(courseId);
        return ResponseEntity.ok().body("User Course deletado com sucesso");
    }

}
