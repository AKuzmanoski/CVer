package com.cver.team.web;

import com.cver.team.model.Person;
import com.cver.team.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/users")
public class PersonController {

    @Autowired
    PersonService personService;

    @RequestMapping(value = "/{email}", method = RequestMethod.GET)
    public Person getUserByEmail(@PathVariable("email") String email) {

        return personService.getPersonByLoginEmailWithoutPassword(email);

    }

}
