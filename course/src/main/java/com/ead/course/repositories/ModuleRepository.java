package com.ead.course.repositories;

import java.util.UUID;

import com.ead.course.models.ModuleModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModuleRepository extends JpaRepository<ModuleModel, UUID> {
    
}
