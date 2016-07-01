package com.cver.team.auth;

import com.cver.team.config.StaticConstants;
import com.cver.team.model.Person;
import com.cver.team.model.Provider;
import com.cver.team.model.Role;
import com.cver.team.services.SocialNetworkService;
import com.cver.team.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * Created by Dimitar on 4/16/2016.
 */
public class SocialNetworkLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    private Provider provider;
    private Role role;
    private SocialNetworkService socialNetworkService;

    public SocialNetworkLoginSuccessHandler(Provider provider, Role role, SocialNetworkService socialNetworkService) {
        this.provider = provider;
        this.role = role;
        this.socialNetworkService = socialNetworkService;
    }

    @Autowired
    OAuth2ClientContext oauth2ClientContext;

    @Autowired
    PersonService personService;



    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        String email = socialNetworkService.getUserEmailFromAuthToken(oauth2ClientContext.getAccessToken());
        Person person = personService.getPersonByLoginEmail(email);

        if(person == null) { //the user is logging in for the first time

        person = new Person();
        person.setLoginEmail(email);
        person.setProvider(provider);
        person.setRole(role);

        personService.savePerson(person);
        }

        HttpSession session = httpServletRequest.getSession();
        session.setAttribute("user", person);

        httpServletResponse.addHeader("Access-Control-Allow-Origin", StaticConstants.ANGULAR_APP_URL);
        httpServletResponse.addHeader("Access-Control-Allow-Credentials", "true");
        httpServletResponse.setStatus(HttpServletResponse.SC_ACCEPTED);

    }

}
