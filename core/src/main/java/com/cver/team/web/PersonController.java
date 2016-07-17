package com.cver.team.web;

import com.cver.team.model.Person;
import com.cver.team.services.PersonService;
import com.cver.team.web.ResponseExceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class PersonController {

    @Autowired
    PersonService personService;

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public Person getUserByEmail(@RequestParam("email") String email) {

        Person person = personService.getPersonByLoginEmailWithoutPassword(email);
        if (person == null)
            throw new ResourceNotFoundException();
        return person;
    }
}
