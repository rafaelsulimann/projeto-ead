package com.ead.course.repositories;

import java.util.UUID;

import com.ead.course.models.LessonModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonRepository extends JpaRepository<LessonModel, UUID> {
    
}
