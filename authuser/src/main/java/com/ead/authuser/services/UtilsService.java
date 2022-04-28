package com.ead.authuser.services;

import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UtilsService {

    public String REQUEST_URI = "http://localhost:8082";

    public String createUrl(UUID userId, Pageable pageable){
        return REQUEST_URI + "/courses?userId=" + userId + "&page=" + pageable.getPageNumber() + "&size="
        + pageable.getPageSize();
    }
    
}
