package com.cver.team.services;

import com.cver.team.model.Person;

/**
 * Created by Dimitar on 6/29/2016.
 */
public interface PersonService {

    void savePerson(Person person);

    Person getPersonByLoginEmail(String email);

    boolean isEmailTaken(String email);
}
