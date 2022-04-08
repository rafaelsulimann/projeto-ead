package com.ead.course.services;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import com.ead.course.dtos.CourseDto;
import com.ead.course.models.CourseModel;
import com.ead.course.models.LessonModel;
import com.ead.course.models.ModuleModel;
import com.ead.course.repositories.CourseRepository;
import com.ead.course.repositories.LessonRepository;
import com.ead.course.repositories.ModuleRepository;
import com.ead.course.services.exceptions.CourseNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private ModuleRepository moduleRepository;

    @Autowired
    private LessonRepository lessonRepository;

    public List<CourseModel> findAll(){
        return courseRepository.findAll();
    }

    public CourseModel findById(UUID courseId){
        Optional<CourseModel> obj = courseRepository.findById(courseId);
        return obj.orElseThrow(() -> new CourseNotFoundException(courseId));
    }

    public CourseModel insert(CourseModel obj){
        obj.setCreationDate(LocalDateTime.now(ZoneId.of("UTC")));
        obj.setLasUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));
        return obj;
    }

    public CourseModel save(CourseModel obj){
        return courseRepository.save(obj);
    }

    public CourseModel updateCourse(UUID courseId, CourseModel obj){
        CourseModel entity = findById(courseId);
        entity.setName(obj.getName());
        entity.setDescription(obj.getDescription());
        entity.setCourseLevel(obj.getCourseLevel());
        entity.setCourseStatus(obj.getCourseStatus());
        entity.setUserInstructor(obj.getUserInstructor());
        entity.setLasUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));
        return entity;
    }

    @Transactional
    public void delete(CourseModel obj){
        List<ModuleModel> moduleModelList = moduleRepository.findAllModulesIntoCourse(obj.getCourseId());
        if(!moduleModelList.isEmpty()){
            for(ModuleModel module : moduleModelList){
                List<LessonModel> lessonModelList = lessonRepository.findAllLessonsIntoModule(module.getModuleId());
                if(!lessonModelList.isEmpty()){
                    lessonRepository.deleteAll(lessonModelList);
                }
            }
            moduleRepository.deleteAll(moduleModelList);
        }
        courseRepository.delete(obj);
    }

    public CourseModel fromDto(CourseDto courseDto){
        CourseModel obj = new CourseModel();
        BeanUtils.copyProperties(courseDto, obj);
        return obj;
    }    
}