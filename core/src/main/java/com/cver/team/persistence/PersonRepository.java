package com.cver.team.persistence;

import com.cver.team.model.Person;


public interface PersonRepository {

    void savePerson(Person person);

    boolean isEmailTaken(String email);

    Person getPersonByLoginEmail(String email);

}
