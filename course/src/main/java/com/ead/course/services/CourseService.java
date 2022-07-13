package com.ead.course.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.ead.course.models.CourseModel;
import com.ead.course.models.UserModel;

public interface CourseService {

    Page<CourseModel> findAll(Specification<CourseModel> spec, Pageable pageable);

    List<CourseModel> findAllCourses();

    Optional<CourseModel> findById(UUID courseId);

    CourseModel save(CourseModel obj);

    void delete(CourseModel obj);

    boolean existsByCourseAndUser(UUID courseId, UUID userId);

    void saveSubscriptionUserInCourse(UUID courseId, UUID userId);

    void saveSubscriptionUserInCourseAndSendNotification(CourseModel courseModel, UserModel userModel);

}
