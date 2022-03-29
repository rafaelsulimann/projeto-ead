package com.ead.authuser.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.ead.authuser.dtos.UserDto;
import com.ead.authuser.models.UserModel;
import com.ead.authuser.repositories.UserRepository;
import com.ead.authuser.services.exceptions.NotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

    public UserModel fromDTO (UserDto objDTO){
        UserModel obj = new UserModel();
        BeanUtils.copyProperties(objDTO, obj);
        return obj;
    }

    public boolean existsByName(String name){
        return repository.existsByName(name);
    }

    public boolean existsByEmail(String email){
        return repository.existsByEmail(email);
    }
    
}
