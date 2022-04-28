package com.ead.course.controllers;

import java.util.UUID;

import com.ead.course.clients.CourseClient;
import com.ead.course.dtos.UserDto;

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
public class CourseUserController {

    @Autowired
    private CourseClient courseClient;

    @GetMapping(value = "/courses/{courseId}/users")
    public ResponseEntity<Page<UserDto>> findAllUsersByUser(@PathVariable UUID courseId,
            @PageableDefault(page = 0, size = 10, sort = "userId", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok().body(courseClient.findAllUsersByCourse(courseId, pageable));
    }

}
