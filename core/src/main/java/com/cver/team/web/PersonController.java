package com.cver.team.web;

import com.cver.team.model.entity.Person;
import com.cver.team.services.PersonService;
import com.cver.team.web.ResponseExceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/users")
public class PersonController {

    @Autowired
    PersonService personService;

    @RequestMapping(method = RequestMethod.GET)
    public Person getUserByEmail(@RequestParam("email") String email) {
        Person person = personService.getPersonByLoginEmailWithoutPassword(email);
        System.out.println(person);
        if (person == null)
            throw new ResourceNotFoundException();
        return person;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Person getUserById(@PathVariable String id) {
        Person person = personService.getPersonById(id);
        if (person == null)
            throw new ResourceNotFoundException();
        return person;
    }

    @RequestMapping(value = "/me", method = RequestMethod.GET)
    public Person getMe(HttpSession session) {
        Person person = (Person) session.getAttribute("user");
        if (person != null)
            return person;
        throw new ResourceNotFoundException();
    }

    @RequestMapping(value = "/{id}/watchLater", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void watchLater(@RequestBody Person person, @PathVariable String id, @RequestParam String entityId) {
        personService.watchLater(id, entityId);
    }
}
