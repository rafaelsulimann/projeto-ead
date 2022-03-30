package com.ead.authuser.services;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.ead.authuser.dtos.UserDto;
import com.ead.authuser.models.UserModel;
import com.ead.authuser.models.enums.UserStatus;
import com.ead.authuser.models.enums.UserType;
import com.ead.authuser.repositories.UserRepository;
import com.ead.authuser.services.exceptions.NotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<UserModel> findAll(){
        return repository.findAll();
    }

    public UserModel findById(UUID id){        
        Optional<UserModel> obj = repository.findById(id);
        return obj.orElseThrow(() -> new NotFoundException(id));  
    }

    public void delete(UUID id){
        repository.deleteById(id);
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

    public UserModel updateUser(UUID id, UserModel obj){
        UserModel entity = findById(id);
        entity.setFullName(obj.getFullName());
        entity.setPhoneNumber(obj.getPhoneNumber());
        entity.setLastUpdateTime(LocalDateTime.now(ZoneId.of("UTC")));
        return entity;
    }

    public UserModel updatePassword(UUID id, UserModel obj){
        UserModel entity = findById(id);
        entity.setPassword(obj.getPassword());
        entity.setLastUpdateTime(LocalDateTime.now(ZoneId.of("UTC")));
        return entity;
    }

    public UserModel updateImage(UUID id, UserModel obj){
        UserModel entity = findById(id);
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

    public Page<UserModel> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }
    
}
