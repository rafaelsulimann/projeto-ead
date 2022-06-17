package com.ead.course.controllers;

import java.net.URI;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ead.course.dtos.CourseDto;
import com.ead.course.models.CourseModel;
import com.ead.course.services.CourseService;
import com.ead.course.specifications.SpecificationTemplate;
import com.ead.course.validations.CourseValidator;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private CourseValidator courseValidator;

    @GetMapping
    public ResponseEntity<Page<CourseModel>> findAllCourses(SpecificationTemplate.CourseSpec spec,
            @PageableDefault(page = 0, size = 10, sort = "creationDate", direction = Sort.Direction.ASC) Pageable pageable,
            @RequestParam(required = false) UUID userId) {
        Page<CourseModel> courseModelPage;
        if(userId != null) {
            courseModelPage = courseService.findAll(SpecificationTemplate.courseUserId(userId).and(spec), pageable);
        }
        else{
            courseModelPage = courseService.findAll(spec, pageable);
        }        
        return ResponseEntity.ok().body(courseModelPage);
    }

    @GetMapping(value = "/{courseId}")
    public ResponseEntity<CourseModel> findCourseById(@PathVariable UUID courseId) {
        CourseModel obj = courseService.findById(courseId);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<Object> saveCourse(@RequestBody CourseDto courseDto, Errors errors) {
        log.debug("POST saveCourse courseDto received {} ", courseDto.toString());
        courseValidator.validate(courseDto, errors);
        if (errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.getAllErrors());
        }
        CourseModel obj = courseService.fromDto(courseDto);
        obj = courseService.insert(obj);
        obj = courseService.save(obj);
        log.debug("POST saveCourse courseId saved {} ", obj.getCourseId());
        log.info("Course saved successfully courseId {} ", obj.getCourseId());
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{courseId}").buildAndExpand(obj.getCourseId())
                .toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @PutMapping(value = "/{courseId}")
    public ResponseEntity<CourseModel> updateCourse(@PathVariable UUID courseId,
            @RequestBody @Valid CourseDto courseDto) {
        log.debug("PUT updateCourse courseDto received {} ", courseDto.toString());
        CourseModel obj = courseService.fromDto(courseDto);
        obj = courseService.updateCourse(courseId, obj);
        obj = courseService.save(obj);
        log.debug("PUT updateCourse courseId saved {} ", obj.getCourseId());
        log.info("Course saved successfully courseId {} ", obj.getCourseId());
        return ResponseEntity.ok().body(obj);
    }

    @DeleteMapping(value = "/{courseId}")
    public ResponseEntity<Object> deleteCourse(@PathVariable UUID courseId) {
        log.debug("DELETE deleteCourse courseId received {} ", courseId);
        CourseModel obj = courseService.findById(courseId);
        courseService.delete(obj);
        log.debug("DELETE deleteCourse courseId saved {} ", courseId);
        log.info("Course deleted successfully courseId {} ", courseId);
        return ResponseEntity.ok().body("Curso deletado com sucesso");
    }

}
