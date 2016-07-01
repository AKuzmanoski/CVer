package com.cver.team.web;

import com.cver.team.model.Person;
import com.cver.team.model.Provider;
import com.cver.team.model.Role;
import com.cver.team.services.PersonService;
import com.cver.team.validation.UserRegistrationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.Map;

/**
 * Created by Dimitar on 6/3/2016.
 */
@Controller
public class RegistrationController {


    @Autowired
    UserRegistrationValidator userRegistrationValidator;

    @Autowired
    PersonService personService;


@RequestMapping(value = "/testPost", method = RequestMethod.POST)
@ResponseBody
public Map<String,String> post() {
    System.out.println("a post went through");
    return Collections.singletonMap("key","value");
}

@RequestMapping(value="/register", method=RequestMethod.POST)
@ResponseBody
public void registration(@RequestParam("email") String email,
                                        @RequestParam("password") String password,
                                        @RequestParam("repeatPassword") String repeatPassword,
                                        HttpServletResponse httpServletResponse) {

    boolean result = userRegistrationValidator.validate(email,password,repeatPassword);

    if(result) {

        Person person = new Person();
        person.setLoginEmail(email);
        person.setPassword(password);
        person.setProvider(Provider.LOCAL);
        person.setRole(Role.ROLE_USER);
        personService.savePerson(person);
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);

    }
    else
        httpServletResponse.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);

}


}


