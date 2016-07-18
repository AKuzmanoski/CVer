package com.cver.team.persistence;

import com.cver.team.model.Person;


public interface PersonRepository {

    Person savePerson(Person person);

    boolean isEmailTaken(String email);

    Person getPersonByLoginEmail(String email);

    Person deletePerson(Person person);

}
