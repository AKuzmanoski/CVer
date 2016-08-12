package com.cver.team.services.impl;

import com.cver.team.model.entity.Person;
import com.cver.team.services.SocialNetworkService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;



@Service
public class LinkedInServiceImpl implements SocialNetworkService {



    public static final String linkedInAccessURL = "https://api.linkedin.com/v1/people/~:(id,first-name,last-name,email-address)?format=json";


    @Override
    public Person getUserInfoFromAuthToken(OAuth2AccessToken token) {
        return null;
    }

    @Override
    public String getUserEmailFromAuthToken(OAuth2AccessToken token) {

        MultiValueMap<String,String> headers = new LinkedMultiValueMap<String,String>();
        headers.add("Authorization","Bearer "+token.getValue());
        HttpEntity requestEntity = new HttpEntity(headers);
        ResponseEntity<Map> result = new RestTemplate().exchange(
                "https://api.linkedin.com/v1/people/~:(id,first-name,last-name,email-address)?format=json",
                HttpMethod.GET,
                requestEntity,
                Map.class);
        Map resultMap = result.getBody();

        String email = (String)resultMap.get("emailAddress");
       // String firstName = (String)resultMap.get("firstName");
      //  String lastName = (String)resultMap.get("lastName");

         return email;
    }
}
