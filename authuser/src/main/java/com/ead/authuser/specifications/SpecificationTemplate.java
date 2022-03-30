package com.ead.authuser.specifications;

import com.ead.authuser.models.UserModel;

import org.springframework.data.jpa.domain.Specification;

import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;

public class SpecificationTemplate {
    
    @And({
        @Spec(path = "userType", spec = Equal.class),
        @Spec(path = "userStatus", spec = Equal.class),
        @Spec(path = "email", spec = Like.class),
        @Spec(path = "userName", spec = Like.class),
        @Spec(path = "cpf", spec = Equal.class)
    })
    public interface UserSpec extends Specification<UserModel>{}
}
