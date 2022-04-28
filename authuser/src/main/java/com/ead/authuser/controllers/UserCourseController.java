package com.ead.authuser.controllers;

import java.util.UUID;

import com.ead.authuser.clients.UserClient;
import com.ead.authuser.dtos.CourseDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserCourseController {

    @Autowired
    private UserClient userClient;

    @GetMapping(value = "/users/{userId}/courses")
    public ResponseEntity<Page<CourseDto>> findAllCourseByUsers(
                                    @PathVariable UUID userId,
                                    @PageableDefault(page = 0, size = 10, sort = "courseId", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok().body(userClient.findAllCoursesByUser(userId, pageable));
    }

}
