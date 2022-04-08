package com.ead.course.controllers;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import com.ead.course.dtos.ModuleDto;
import com.ead.course.models.CourseModel;
import com.ead.course.models.ModuleModel;
import com.ead.course.services.CourseService;
import com.ead.course.services.ModuleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class ModuleController {

    @Autowired
    private ModuleService moduleService;

    @Autowired
    private CourseService courseService;

    @GetMapping(value = "/courses/{courseId}/modules")
    public ResponseEntity<List<ModuleModel>> findAllModulesIntoCourse(@PathVariable UUID courseId){
        courseService.findById(courseId);
        List<ModuleModel> modulesList = moduleService.findAllModulesIntoCourse(courseId);
        return ResponseEntity.ok().body(modulesList);
    }

    @GetMapping(value = "/courses/{courseId}/modules/{moduleId}")
    public ResponseEntity<ModuleModel> findModuleIntoCourse(@PathVariable UUID courseId, @PathVariable UUID moduleId){
        courseService.findById(courseId);
        ModuleModel obj = moduleService.findModuleIntoCourse(courseId, moduleId);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping(value = "/courses/{courseId}/modules")
    public ResponseEntity<ModuleModel> saveModule(@PathVariable UUID courseId, @RequestBody @Valid ModuleDto moduleDto){
        CourseModel course = courseService.findById(courseId);
        ModuleModel obj = moduleService.fromDto(moduleDto);
        obj = moduleService.insert(course, obj);
        obj = moduleService.save(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{moduleId}").buildAndExpand(obj.getModuleId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @PutMapping(value = "/courses/{courseId}/modules/{moduleId}")
    public ResponseEntity<ModuleModel> updateModule(@PathVariable UUID courseId, @PathVariable UUID moduleId, @RequestBody @Valid ModuleDto moduleDto){
        courseService.findById(courseId);
        ModuleModel obj = moduleService.fromDto(moduleDto);
        obj = moduleService.updateModule(courseId, moduleId, obj);
        obj = moduleService.save(obj);
        return ResponseEntity.ok().body(obj);
    }

    @DeleteMapping(value = "/courses/{courseId}/modules/{moduleId}")
    public ResponseEntity<Object> deleteModule(@PathVariable UUID courseId, @PathVariable UUID moduleId){
        ResponseEntity<ModuleModel> obj = findModuleIntoCourse(courseId, moduleId);
        moduleService.delete(obj.getBody());
        return ResponseEntity.ok().body("Módulo deletado com sucesso");
    }
    
}
