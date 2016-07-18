package com.cver.team.services.impl;

import com.cver.team.model.Person;
import com.cver.team.model.literal.Identifier;
import com.cver.team.persistence.PersonRepository;
import com.cver.team.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;



@Service
public class PersonServiceImpl implements PersonService {


    @Autowired
    PersonRepository personRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Person saveNewPerson(Person person) {

        if(person.getPassword() != null)
        person.setPassword(bCryptPasswordEncoder.encode(person.getPassword()));

        person.setIdentifier(new Identifier());
        person.getIdentifier().setId(UUID.randomUUID().toString());

       return  personRepository.savePerson(person);
    }

    @Override
    public Person getPersonByLoginEmail(String email) {
        return personRepository.getPersonByLoginEmail(email);
    }

    @Override
    public boolean isEmailTaken(String email) {
        return personRepository.isEmailTaken(email);
    }

    @Override
    public Person getPersonByLoginEmailWithoutPassword(String email) {
        Person person = getPersonByLoginEmail(email);
        if(person != null)
        person.setPassword("");
        return person;
    }

    @Override
    public Person deletePerson(Person person) {
        return null;
    }
}
