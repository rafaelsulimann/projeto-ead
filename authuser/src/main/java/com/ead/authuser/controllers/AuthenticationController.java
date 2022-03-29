package com.ead.authuser.controllers;

import java.net.URI;
import java.time.Instant;

import com.ead.authuser.dtos.UserDto;
import com.ead.authuser.models.UserModel;
import com.ead.authuser.models.enums.UserStatus;
import com.ead.authuser.models.enums.UserType;
import com.ead.authuser.services.UserService;
import com.ead.authuser.services.exceptions.ExistsByEmailException;
import com.ead.authuser.services.exceptions.ExistsByUserNameException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/auth")
public class AuthenticationController {

    @Autowired
    private UserService service;

    @PostMapping(value = "/signup")
    public ResponseEntity<UserModel> registerUser(@RequestBody UserDto userDto){        
        if(service.existsByName(userDto.getName())){
            throw new ExistsByUserNameException(userDto.getName());
        }
        if(service.existsByEmail(userDto.getEmail())){
            throw new ExistsByEmailException(userDto.getEmail());
        }
        UserModel obj = service.fromDTO(userDto);
        obj.setUserStatus(UserStatus.ACTIVE);
        obj.setUserType(UserType.STUDENT);
        obj.setCreationDate(Instant.now());
        obj.setLastUpdateTime(Instant.now());
        obj = service.save(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }    
}


