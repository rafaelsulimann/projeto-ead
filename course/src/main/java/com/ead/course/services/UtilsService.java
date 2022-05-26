package com.ead.course.services;

import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UtilsService {

    public String createUrlGetAllUsersByCourse(UUID courseId, Pageable pageable){
        return "/users?courseId=" + courseId + "&page=" + pageable.getPageNumber() + "&size=" + pageable.getPageSize();
    }
    
}
