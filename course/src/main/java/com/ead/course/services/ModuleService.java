package com.ead.course.services;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import com.ead.course.dtos.ModuleDto;
import com.ead.course.models.CourseModel;
import com.ead.course.models.LessonModel;
import com.ead.course.models.ModuleModel;
import com.ead.course.repositories.LessonRepository;
import com.ead.course.repositories.ModuleRepository;
import com.ead.course.services.exceptions.ModuleNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModuleService {

    @Autowired
    private ModuleRepository moduleRepository;

    @Autowired
    private LessonRepository lessonRepository;

    public List<ModuleModel> findAllModulesIntoCourse(UUID couseId){
        return moduleRepository.findAllModulesIntoCourse(couseId);
    }

    public ModuleModel findModuleIntoCourse(UUID courseId, UUID moduleId){
        Optional<ModuleModel> obj = moduleRepository.findModuleIntoCourse(courseId, moduleId);
        return obj.orElseThrow(() -> new ModuleNotFoundException(moduleId));
    }

    public ModuleModel insert(CourseModel courseModel, ModuleModel moduleModel){
        moduleModel.setCreationDate(LocalDateTime.now(ZoneId.of("UTC")));
        moduleModel.setCourse(courseModel);
        return moduleModel;
    }

    public ModuleModel save(ModuleModel moduleModel){
        return moduleRepository.save(moduleModel);
    }

    public ModuleModel updateModule(UUID courseId, UUID moduleId, ModuleModel obj){
        ModuleModel entity = findModuleIntoCourse(courseId, moduleId);
        entity.setTitle(obj.getTitle());
        entity.setDescription(obj.getDescription());
        return entity;
    }

    @Transactional
    public void delete(ModuleModel moduleModel){
        List<LessonModel> lessonModelList = lessonRepository.findAllLessonsIntoModule(moduleModel.getModuleId());
        if(!lessonModelList.isEmpty()){
            lessonRepository.deleteAll(lessonModelList);
        }
        moduleRepository.delete(moduleModel);
    }

    public ModuleModel fromDto(ModuleDto moduleDto){
        ModuleModel obj = new ModuleModel();
        BeanUtils.copyProperties(moduleDto, obj);
        return obj;
    }
}
