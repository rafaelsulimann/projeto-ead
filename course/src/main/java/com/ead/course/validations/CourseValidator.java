package com.ead.course.validations;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.client.HttpStatusCodeException;

import com.ead.course.clients.AuthUserClient;
import com.ead.course.dtos.CourseDto;
import com.ead.course.dtos.UserDto;
import com.ead.course.models.enums.UserType;

@Component
public class CourseValidator implements Validator {

    @Autowired
    @Qualifier("defaultValidator")
    private Validator validator;

    @Autowired
    private AuthUserClient authUserClient;

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        CourseDto courseDto = (CourseDto) target;
        validator.validate(courseDto, errors);
        if(!errors.hasErrors()){
            validateUserInstructor(courseDto.getUserInstructor(), errors);
        }
    }

    private void validateUserInstructor(UUID userInstructor, Errors errors){
        ResponseEntity<UserDto> userDto;
            try{
                userDto = authUserClient.findOneUserById(userInstructor);
                if(userDto.getBody().getUserType().equals(UserType.STUDENT)){
                    errors.rejectValue("UserInstructor", "UserInstructorError", "Usuário precisa ser do tipo STUDENT ou ADMIN");
                }
            }catch(HttpStatusCodeException e){
                if(e.getStatusCode().equals(HttpStatus.NOT_FOUND)){
                    errors.rejectValue("UserInstructor", "UserInstructorError", "Usuário não encontrado");
                }
            }
    }
    
}
