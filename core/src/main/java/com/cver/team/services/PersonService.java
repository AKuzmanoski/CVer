package com.cver.team.services;

import com.cver.team.model.entity.Person;

/**
 * Created by Dimitar on 6/29/2016.
 */
public interface PersonService {

    Person saveNewPerson(Person person);

    Person getPersonByLoginEmail(String email);

    boolean isEmailTaken(String email);

    Person getPersonByLoginEmailWithoutPassword(String email);

    Person deletePerson(Person person);


    Person getPersonById(String id);

    void watchLater(String id, String entityId);
}
