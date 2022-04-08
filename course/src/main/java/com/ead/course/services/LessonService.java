package com.ead.course.services;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.ead.course.dtos.LessonDto;
import com.ead.course.models.LessonModel;
import com.ead.course.models.ModuleModel;
import com.ead.course.repositories.LessonRepository;
import com.ead.course.services.exceptions.LessonNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LessonService {

    @Autowired
    private LessonRepository repository;

    public List<LessonModel> findAllLessonsIntoModule(UUID moduleId){
        return repository.findAllLessonsIntoModule(moduleId);
    }

    public LessonModel findLessonIntoModule (UUID moduleId, UUID lessonId){
        Optional<LessonModel> obj = repository.findLessonIntoModule(moduleId, lessonId);
        return obj.orElseThrow(() -> new LessonNotFoundException(lessonId));
    }

    public LessonModel save(LessonModel obj){
        return repository.save(obj);
    }

    public void delete(UUID lessonId){
        repository.deleteById(lessonId);
    }

    public LessonModel updateLesson(UUID moduleId, UUID lessonId, LessonModel obj){
        LessonModel entity = findLessonIntoModule(moduleId, lessonId);
        entity.setTitle(obj.getTitle());
        entity.setDescription(obj.getDescription());
        entity.setVideoUrl(obj.getVideoUrl());
        return entity;
    }

    public LessonModel insert(ModuleModel moduleModel, LessonModel lessonModel){        
        lessonModel.setCreationDate(LocalDateTime.now(ZoneId.of("UTC")));
        lessonModel.setModule(moduleModel);
        return lessonModel;
    }

    public LessonModel fromDto(LessonDto lessonDto){
        LessonModel obj = new LessonModel();
        BeanUtils.copyProperties(lessonDto, obj);
        return obj;
    }
    
}
