package com.cver.team.services;

import com.cver.team.model.Person;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

/**
 * Created by Dimitar on 6/23/2016.
 */
public interface SocialNetworkService {

    Person getUserInfoFromAuthToken(OAuth2AccessToken token);
    String getUserEmailFromAuthToken(OAuth2AccessToken token);

}
