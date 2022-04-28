package com.ead.course.services;

import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UtilsService {

    public String REQUEST_URI = "http://localhost:8087";

    public String createUrl(UUID courseId, Pageable pageable){
        return REQUEST_URI + "/users?courseId=" + courseId + "&page=" + pageable.getPageNumber() + "&size=" + pageable.getPageSize();
    }
    
}
