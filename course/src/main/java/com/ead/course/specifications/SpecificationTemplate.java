package com.ead.course.specifications;

import com.ead.course.models.CourseModel;

import org.springframework.data.jpa.domain.Specification;

import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;

public class SpecificationTemplate {
    
    @And({
        @Spec(path = "couseStatus", spec = Equal.class),
        @Spec(path = "courseLevel", spec = Equal.class),
        @Spec(path = "name", spec = Like.class)
    })
    public interface CourseSpec extends Specification<CourseModel>{}
    
}
