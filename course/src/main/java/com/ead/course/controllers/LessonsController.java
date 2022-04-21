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

import lombok.extern.log4j.Log4j2;

@Log4j2
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
        log.debug("POST saveLesson lessonDto received {} ", lessonDto.toString());
        ModuleModel module = moduleService.findById(moduleId);
        LessonModel obj = lessonService.fromDto(lessonDto);
        obj = lessonService.insert(module, obj);
        obj = lessonService.save(obj);
        log.debug("POST saveLesson lessonModel saved {} ", lessonDto.toString());
        log.info("Lesson saved successfully lessonId {} ", obj.getLessonId());
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{lessonId}").buildAndExpand(obj.getLessonId())
                .toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @PutMapping(value = "/modules/{moduleId}/lessons/{lessonId}")
    public ResponseEntity<LessonModel> updateLesson(@PathVariable UUID moduleId, @PathVariable UUID lessonId,
            @RequestBody @Valid LessonDto lessonDto) {
        log.debug("PUT updateLesson lessonDto received {} ", lessonDto.toString());
        moduleService.findById(moduleId);
        LessonModel obj = lessonService.fromDto(lessonDto);
        obj = lessonService.updateLesson(moduleId, lessonId, obj);
        obj = lessonService.save(obj);
        log.debug("PUT updateLesson lessonModel saved {} ", lessonDto.toString());
        log.info("Lesson saved successfully lessonId {} ", obj.getLessonId());
        return ResponseEntity.ok().body(obj);
    }

    @DeleteMapping(value = "modules/{moduleId}/lessons/{lessonId}")
    public ResponseEntity<Object> deleteLesson(@PathVariable UUID moduleId, @PathVariable UUID lessonId) {
        log.debug("DELETE deleteLesson lessonId received {} ", lessonId);
        findLessonIntoModule(moduleId, lessonId);
        lessonService.delete(lessonId);
        log.debug("DELETE deleteLesson lessonId saved {} ", lessonId);
        log.info("Lesson deleted successfully lessonId {} ", lessonId);
        return ResponseEntity.ok().body("Aula deletada com sucesso");
    }
}