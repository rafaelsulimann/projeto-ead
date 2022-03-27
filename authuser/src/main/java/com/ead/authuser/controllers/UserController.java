package com.ead.authuser.controllers;

import java.util.List;
import java.util.UUID;

import com.ead.authuser.models.UserModel;
import com.ead.authuser.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
}
