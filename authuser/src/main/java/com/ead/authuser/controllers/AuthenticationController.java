package com.ead.authuser.controllers;

import java.net.URI;

import com.ead.authuser.dtos.UserDto;
import com.ead.authuser.models.UserModel;
import com.ead.authuser.services.UserService;
import com.ead.authuser.services.exceptions.ExistsByCpfException;
import com.ead.authuser.services.exceptions.ExistsByEmailException;
import com.ead.authuser.services.exceptions.ExistsByUserNameException;
import com.fasterxml.jackson.annotation.JsonView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/auth")
public class AuthenticationController {

    @Autowired
    private UserService service;

    @PostMapping(value = "/signup")
    public ResponseEntity<UserModel> registerUser(
            @RequestBody @Validated(UserDto.UserView.RegistrantionPost.class) @JsonView(UserDto.UserView.RegistrantionPost.class) UserDto userDto) {
        if (service.existsByUserName(userDto.getUserName())) {
            log.warn("Username {} já existe", userDto.getUserName());
            throw new ExistsByUserNameException(userDto.getUserName());
        }
        if (service.existsByEmail(userDto.getEmail())) {
            log.warn("Email {} já existe", userDto.getEmail());
            throw new ExistsByEmailException(userDto.getEmail());
        }
        if (service.existsByCpf(userDto.getCpf())) {
            log.warn("CPF {} já existe", userDto.getCpf());
            throw new ExistsByCpfException(userDto.getCpf());
        }
        log.debug("POST registerUser userDto received {} ", userDto.toString());
        UserModel obj = service.fromDTO(userDto);
        obj = service.insert(obj);
        obj = service.save(obj);
        log.debug("POST registerUser userModel saved {} ", obj.toString());
        log.info("User saved successfully userId {} ", obj.getUserId());
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{userId}").buildAndExpand(obj.getUserId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @GetMapping(value = "/")
    public String index(){
        log.trace("TRACE");
        log.debug("DEBUG");
        log.info("INFO");
        log.warn("WARN");
        log.error("ERROR");
        return "Logging Spring Boot...";
    }
}
