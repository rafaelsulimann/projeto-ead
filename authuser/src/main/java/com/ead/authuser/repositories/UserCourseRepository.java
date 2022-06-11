package com.ead.authuser.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ead.authuser.models.UserCourseModel;
import com.ead.authuser.models.UserModel;

public interface UserCourseRepository extends JpaRepository<UserCourseModel, UUID>{

    boolean existsByUserModelAndCourseId(UserModel userModel, UUID courseId);

    boolean existsByCourseId(UUID courseId);

    @Query(value = "select * from tb_users_courses where user_id = :userId", nativeQuery = true)
    List<UserCourseModel> findAllUsersCoursesIntoUser(@Param("userId") UUID userId);

    void deleteAllByCourseId(UUID courseId);
    
}
