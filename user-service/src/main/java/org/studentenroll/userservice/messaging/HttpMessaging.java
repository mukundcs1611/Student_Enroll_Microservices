package org.studentenroll.userservice.messaging;

import org.omg.CORBA.Any;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.studentenroll.userservice.entity.UserCourse;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mukund chavali
 **/
@Service
public class HttpMessaging  {
    final RestTemplate restTemplate;

    @Autowired
    public HttpMessaging(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public List<UserCourse> getCourses(String url, List<String> param){
        HttpEntity h=new HttpEntity(param);
        return restTemplate.exchange(url,HttpMethod.POST, h, new ParameterizedTypeReference<List<UserCourse>>(){}).getBody();
    }

}
