package com.ead.authuser.controllers;

import java.util.UUID;

import com.ead.authuser.dtos.UserDto;
import com.ead.authuser.models.UserModel;
import com.ead.authuser.services.UserService;
import com.ead.authuser.services.exceptions.PasswordException;
import com.ead.authuser.specifications.SpecificationTemplate;
import com.fasterxml.jackson.annotation.JsonView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Log4j2
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<Page<UserModel>> findAllUsers(SpecificationTemplate.UserSpec spec,
            @PageableDefault(page = 0, size = 10, sort = "userId", direction = Sort.Direction.ASC) Pageable pageable,
            @RequestParam(required = false) UUID courseId) {
        Page<UserModel> userModelPage = null;
        if(courseId != null){
            userModelPage = userService.findAll(SpecificationTemplate.userCourseId(courseId).and(spec), pageable);
        }
        else{
            userModelPage = userService.findAll(spec, pageable);
        }
        if (!userModelPage.isEmpty()) {
            for (UserModel user : userModelPage.toList()) {
                user.add(linkTo(methodOn(UserController.class).findUserById(user.getUserId())).withSelfRel());
            }
        }
        return ResponseEntity.ok().body(userModelPage);
    }

    @GetMapping(value = "/{userId}")
    public ResponseEntity<UserModel> findUserById(@PathVariable UUID userId) {
        UserModel obj = userService.findById(userId);
        return ResponseEntity.ok().body(obj);
    }

    @DeleteMapping(value = "/{userId}")
    public ResponseEntity<Object> deleteUser(@PathVariable UUID userId) {
        log.debug("DELETE deleteUser userId received {} ", userId);
        ResponseEntity<UserModel> user = findUserById(userId);
        userService.delete(user.getBody());
        log.debug("DELETE deleteUser userId saved {} ", userId);
        log.info("User deleted successfully userId {} ", userId);
        return ResponseEntity.ok().body("Usuário deletado com sucesso");
    }

    @PutMapping(value = "/{userId}")
    public ResponseEntity<UserModel> updateUser(@PathVariable UUID userId,
            @RequestBody @Validated(UserDto.UserView.UserPut.class) @JsonView(UserDto.UserView.UserPut.class) UserDto userDto) {
        log.debug("PUT updateUser userDto received {} ", userDto.toString());
        UserModel obj = userService.fromDTO(userDto);
        obj = userService.updateUser(userId, obj);
        obj = userService.save(obj);
        log.debug("PUT updateUser userId saved {} ", obj.getUserId());
        log.info("User saved successfully userId {} ", obj.getUserId());
        return ResponseEntity.ok().body(obj);
    }

    @PutMapping(value = "/{userId}/password")
    public ResponseEntity<Object> updateUserPassword(@PathVariable UUID userId,
            @RequestBody @Validated(UserDto.UserView.PasswordPut.class) @JsonView(UserDto.UserView.PasswordPut.class) UserDto userDto) {
        log.debug("PUT updateUserPassword userDto received {} ", userDto.toString());
        UserModel entity = userService.findById(userId);
        UserModel obj = userService.fromDTO(userDto);
        if (entity.getPassword().equals(userDto.getOldPassword())) {
            entity = userService.updatePassword(userId, obj);
            entity = userService.save(entity);
            log.debug("PUT updateUserPassword userId saved {} ", entity.getUserId());
            log.info("User saved successfully userId {} ", entity.getUserId());
        } else {
            log.warn("Senha {} incorreta", userDto.getOldPassword());
            throw new PasswordException("A senha antiga nao é igual à atual");
        }
        return ResponseEntity.ok().body("Senha alterada com sucesso");
    }

    @PutMapping(value = "/{id}/image")
    public ResponseEntity<Object> updateUserImage(@PathVariable UUID userId,
            @RequestBody @Validated(UserDto.UserView.ImagePut.class) @JsonView(UserDto.UserView.ImagePut.class) UserDto userDto) {
        log.debug("PUT updateUserImage userDto received {} ", userDto.toString());
        UserModel obj = userService.fromDTO(userDto);
        obj = userService.updateImage(userId, obj);
        obj = userService.save(obj);
        log.debug("PUT updateUserImage userId saved {} ", obj.getUserId());
        log.info("User saved successfully userId {} ", obj.getUserId());
        return ResponseEntity.ok().body("Imagem alterada com sucesso");
    }
}
