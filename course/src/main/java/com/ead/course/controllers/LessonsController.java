package com.ead.course.controllers;

import java.net.URI;
import java.util.UUID;

import javax.validation.Valid;

import com.ead.course.dtos.LessonDto;
import com.ead.course.models.LessonModel;
import com.ead.course.models.ModuleModel;
import com.ead.course.services.LessonService;
import com.ead.course.services.ModuleService;
import com.ead.course.specifications.SpecificationTemplate;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class LessonsController {

    @Autowired
    private LessonService lessonService;

    @Autowired
    private ModuleService moduleService;

    @GetMapping(value = "/modules/{moduleId}/lessons")
    public ResponseEntity<Page<LessonModel>> findAllLessonsIntoModule(@PathVariable UUID moduleId,
            SpecificationTemplate.LessonSpec spec,
            @PageableDefault(page = 0, size = 10, sort = "creationDate", direction = Sort.Direction.ASC) Pageable pageable) {
        moduleService.findById(moduleId);
        Page<LessonModel> list = lessonService.findAllLessonsIntoModule(SpecificationTemplate.lessonModuleId(moduleId).and(spec), pageable);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/modules/{moduleId}/lessons/{lessonId}")
    public ResponseEntity<LessonModel> findLessonIntoModule(@PathVariable UUID moduleId, @PathVariable UUID lessonId) {
        moduleService.findById(moduleId);
        LessonModel obj = lessonService.findLessonIntoModule(moduleId, lessonId);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping(value = "/modules/{moduleId}/lessons")
    public ResponseEntity<LessonModel> saveLesson(@PathVariable UUID moduleId,
            @RequestBody @Valid LessonDto lessonDto) {
        ModuleModel module = moduleService.findById(moduleId);
        LessonModel obj = lessonService.fromDto(lessonDto);
        obj = lessonService.insert(module, obj);
        obj = lessonService.save(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{lessonId}").buildAndExpand(obj.getLessonId())
                .toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @PutMapping(value = "/modules/{moduleId}/lessons/{lessonId}")
    public ResponseEntity<LessonModel> updateLesson(@PathVariable UUID moduleId, @PathVariable UUID lessonId,
            @RequestBody @Valid LessonDto lessonDto) {
        moduleService.findById(moduleId);
        LessonModel obj = lessonService.fromDto(lessonDto);
        obj = lessonService.updateLesson(moduleId, lessonId, obj);
        obj = lessonService.save(obj);
        return ResponseEntity.ok().body(obj);
    }

    @DeleteMapping(value = "modules/{moduleId}/lessons/{lessonId}")
    public ResponseEntity<Object> deleteLesson(@PathVariable UUID moduleId, @PathVariable UUID lessonId) {
        findLessonIntoModule(moduleId, lessonId);
        lessonService.delete(lessonId);
        return ResponseEntity.ok().body("Aula deletada com sucesso");
    }
}