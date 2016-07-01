package com.cver.team.services.impl;

import com.cver.team.model.Person;
import com.cver.team.services.SocialNetworkService;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * Created by Dimitar on 6/23/2016.
 */
@Service
public class FacebookServiceImpl implements SocialNetworkService {

    public static final String facebookAccessURL = "https://graph.facebook.com/v2.6/me";


    //This method gets the first name, last name and the email of the user.
    @Override
    public Person getUserInfoFromAuthToken(OAuth2AccessToken token) {
        RestTemplate restTemplate = new RestTemplate();
        StringBuilder queryString = new StringBuilder();

        String requiredFields = "first_name,last_name,id,email";

        queryString.append("access_token=" + token.getValue());
        queryString.append("&fields=" + requiredFields);

        Map result = restTemplate.getForObject(
                facebookAccessURL + "?" + queryString.toString(),Map.class);

        String email = (String)result.get("email");
        String firstName =  (String) result.get("first_name");
        String lastName = (String) result.get("last_name");
        Person person = new Person();
        return person;

    }

    public String getUserEmailFromAuthToken(OAuth2AccessToken token) {
        RestTemplate restTemplate = new RestTemplate();
        StringBuilder queryString = new StringBuilder();

        String requiredFields = "email";
        queryString.append("access_token=" + token.getValue());
        queryString.append("&fields=" + requiredFields);

        Map result = restTemplate.getForObject(
                facebookAccessURL + "?" + queryString.toString(),Map.class);

        String email = (String)result.get("email");
        return email;
    }
}
