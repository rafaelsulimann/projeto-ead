package com.ead.course.clients;

import java.util.List;
import java.util.UUID;

import com.ead.course.dtos.ResponsePageDto;
import com.ead.course.dtos.UserDto;
import com.ead.course.services.UtilsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class AuthUserClient {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UtilsService utilsService;

    @Value("${ead.api.url.authuser}")
    public String REQUEST_URI_AUTHUSER;

    public Page<UserDto> findAllUsersByCourse(UUID courseId, Pageable pageable){
        List<UserDto> searchResult = null;
        ResponseEntity<ResponsePageDto<UserDto>> result = null;
        String url = REQUEST_URI_AUTHUSER + utilsService.createUrlGetAllUsersByCourse(courseId, pageable);
        log.debug("Request URL {} ", url);
        log.info("Request URL {} ", url);
        try{
            ParameterizedTypeReference<ResponsePageDto<UserDto>> responseType = new ParameterizedTypeReference<ResponsePageDto<UserDto>>(){};
            result = restTemplate.exchange(url, HttpMethod.GET, null, responseType);
            searchResult = result.getBody().getContent();
            log.debug("Response number of elements {} " , searchResult.size());
        }
        catch(HttpStatusCodeException e){
            log.error("Error request /users {} ", e);
        }
        log.info("Ending request /users {} ", courseId);
        return result.getBody();
    }

    public ResponseEntity<UserDto> findOneUserById(UUID userId){
        String url = REQUEST_URI_AUTHUSER + "/users/" + userId;
        return restTemplate.exchange(url, HttpMethod.GET, null, UserDto.class);
    }
    
}
