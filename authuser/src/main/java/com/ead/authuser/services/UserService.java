package com.ead.authuser.services;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.ead.authuser.clients.CourseClient;
import com.ead.authuser.dtos.UserDto;
import com.ead.authuser.models.UserCourseModel;
import com.ead.authuser.models.UserModel;
import com.ead.authuser.models.enums.UserStatus;
import com.ead.authuser.models.enums.UserType;
import com.ead.authuser.repositories.UserCourseRepository;
import com.ead.authuser.repositories.UserRepository;
import com.ead.authuser.services.exceptions.UserNotFoundException;

@Service
public class UserService {
     
    @Autowired
    private UserRepository repository;

    @Autowired
    private UserCourseRepository userCourseRepository;

    @Autowired
    private CourseClient courseClient;

    public Page<UserModel> findAll(Specification<UserModel> spec, Pageable pageable) {
        return repository.findAll(spec, pageable);
    }

    public UserModel findById(UUID userId){        
        Optional<UserModel> obj = repository.findById(userId);
        return obj.orElseThrow(() -> new UserNotFoundException(userId));  
    }

    @Transactional
    public void delete(UserModel userModel){
        boolean deleteUserInCourse = false;
        List<UserCourseModel> userCourseModelList = userCourseRepository.findAllUsersCoursesIntoUser(userModel.getUserId());
        if(!userCourseModelList.isEmpty()){
            userCourseRepository.deleteAll(userCourseModelList);
            deleteUserInCourse = true;
        }
        repository.delete(userModel);
        if(deleteUserInCourse){
            courseClient.deleteUserInCourse(userModel.getUserId());
        }
    }

    public UserModel save(UserModel userModel){
        return repository.save(userModel);
    }

    public UserModel insert(UserModel obj){
        obj.setUserStatus(UserStatus.ACTIVE);
        obj.setUserType(UserType.STUDENT);
        obj.setCreationDate(LocalDateTime.now(ZoneId.of("UTC")));
        obj.setLastUpdateTime(LocalDateTime.now(ZoneId.of("UTC")));
        return obj;
    }

    public UserModel updateUser(UUID userId, UserModel obj){
        UserModel entity = findById(userId);
        entity.setFullName(obj.getFullName());
        entity.setPhoneNumber(obj.getPhoneNumber());
        entity.setLastUpdateTime(LocalDateTime.now(ZoneId.of("UTC")));
        return entity;
    }

    public UserModel updatePassword(UUID userId, UserModel obj){
        UserModel entity = findById(userId);
        entity.setPassword(obj.getPassword());
        entity.setLastUpdateTime(LocalDateTime.now(ZoneId.of("UTC")));
        return entity;
    }

    public UserModel updateImage(UUID userId, UserModel obj){
        UserModel entity = findById(userId);
        entity.setImgUrl(obj.getImgUrl());
        entity.setLastUpdateTime(LocalDateTime.now(ZoneId.of("UTC")));
        return entity;
    }

    public UserModel fromDTO (UserDto objDTO){
        UserModel obj = new UserModel();
        BeanUtils.copyProperties(objDTO, obj);
        return obj;
    }

    public boolean existsByUserName(String userName){
        return repository.existsByUserName(userName);
    }

    public boolean existsByEmail(String email){
        return repository.existsByEmail(email);
    }

    public boolean existsByCpf(String cpf){
        return repository.existsByCpf(cpf);
    }

    public UserModel insertInstructor(UserModel obj){
        obj.setUserType(UserType.INSTRUCTOR);
        obj.setLastUpdateTime(LocalDateTime.now(ZoneId.of("UTC")));
        return obj;
    }
    
}
