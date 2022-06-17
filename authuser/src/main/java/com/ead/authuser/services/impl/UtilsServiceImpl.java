package com.ead.authuser.services.impl;

import java.util.UUID;

import org.springframework.data.domain.Pageable;

import com.ead.authuser.services.UtilsService;

public class UtilsServiceImpl implements UtilsService{
    
    public String createUrlGetAllCoursesByUser(UUID userId, Pageable pageable){
        return "/courses?userId=" + userId + "&page=" + pageable.getPageNumber() + "&size="
        + pageable.getPageSize();
    }
}
