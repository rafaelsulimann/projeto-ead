package com.ead.course.controllers;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import com.ead.course.dtos.CourseDto;
import com.ead.course.models.CourseModel;
import com.ead.course.services.CourseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping
    public ResponseEntity<List<CourseModel>> findAllCourses(){
        List<CourseModel> list = courseService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{courseId}")
    public ResponseEntity<CourseModel> findCourseById(@PathVariable UUID courseId){
        CourseModel obj = courseService.findById(courseId);
        return ResponseEntity.ok().body(obj);
    }


    @PostMapping
    public ResponseEntity<CourseModel> saveCourse(@RequestBody @Valid CourseDto courseDto){
        CourseModel obj = courseService.fromDto(courseDto);
        obj = courseService.insert(obj);
        obj = courseService.save(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{courseId}").buildAndExpand(obj.getCourseId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @PutMapping(value = "/{courseId}")
    public ResponseEntity<CourseModel> updateCourse(@PathVariable UUID courseId, @RequestBody @Valid CourseDto courseDto){
        CourseModel obj = courseService.fromDto(courseDto);
        obj = courseService.updateCourse(courseId, obj);
        obj = courseService.save(obj);
        return ResponseEntity.ok().body(obj);
    }

    @DeleteMapping(value = "/{courseId}")
    public ResponseEntity<Object> deleteCourse(@PathVariable UUID courseId){
        CourseModel obj = courseService.findById(courseId);
        courseService.delete(obj);
        return ResponseEntity.ok().body("Curso deletado com sucesso");
    }
    
}
