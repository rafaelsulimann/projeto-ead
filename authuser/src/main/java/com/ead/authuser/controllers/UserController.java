package com.ead.authuser.controllers;

import java.util.List;
import java.util.UUID;

import com.ead.authuser.dtos.UserDto;
import com.ead.authuser.models.UserModel;
import com.ead.authuser.services.UserService;
import com.ead.authuser.services.exceptions.PasswordException;
import com.fasterxml.jackson.annotation.JsonView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/users")
public class UserController {
    
    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<List<UserModel>> findAll(){
        List<UserModel> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserModel> findById(@PathVariable UUID id){
        UserModel obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> delete(@PathVariable UUID id){
        findById(id);
        service.delete(id);
        return ResponseEntity.ok().body("Usuário deletado com sucesso");
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UserModel> updateUser(@PathVariable UUID id, @RequestBody @JsonView(UserDto.UserView.UserPut.class) UserDto userDto){
        UserModel obj = service.fromDTO(userDto);
        obj = service.updateUser(id, obj);
        obj = service.save(obj);
        return ResponseEntity.ok().body(obj);
    }

    @PutMapping(value = "/{id}/password")
    public ResponseEntity<Object> updatePassword(@PathVariable UUID id, @RequestBody @JsonView(UserDto.UserView.PasswordPut.class) UserDto userDto){
        UserModel entity = service.findById(id);
        UserModel obj = service.fromDTO(userDto);
        if(entity.getPassword().equals(userDto.getOldPassword())){
            entity = service.updatePassword(id, obj);
            entity = service.save(entity);                 
        }
        else{
            throw new PasswordException("A senha antiga nao é igual à atual");
        }
        return ResponseEntity.ok().body("Senha alterada com sucesso");        
    }

    @PutMapping(value = "/{id}/image")
    public ResponseEntity<Object> updateImage(@PathVariable UUID id, @RequestBody @JsonView(UserDto.UserView.ImagePut.class) UserDto userDto){
        UserModel obj = service.fromDTO(userDto);       
        obj = service.updateImage(id, obj);
        obj = service.save(obj);                 
        return ResponseEntity.ok().body("Imagem alterada com sucesso");        
    }
}

