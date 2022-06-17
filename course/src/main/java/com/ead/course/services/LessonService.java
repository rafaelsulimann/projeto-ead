package com.ead.course.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.ead.course.models.LessonModel;

public interface LessonService {

    Page<LessonModel> findAllLessonsIntoModule(Specification<LessonModel> spec, Pageable pageable);

    Optional<LessonModel> findLessonIntoModule (UUID moduleId, UUID lessonId);

    List<LessonModel> findAllByModule(UUID moduleId);

    LessonModel save(LessonModel lessonModel);

    void delete(LessonModel lessonModel);
    
}
