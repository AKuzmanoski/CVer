package com.cver.team.validation;

import com.cver.team.persistence.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserRegistrationValidator {


    @Autowired
    PersonRepository personRepository;

    private final int minPasswordLength = 6;
    private final int maxPasswordLength = 12;



    public boolean validate(String email, String password, String repeatPassword) {

        if(!password.equals(repeatPassword))
            return false;

        if(password.length() < minPasswordLength || password.length() > maxPasswordLength)
            return false;

        if(personRepository.isEmailTaken(email))
        return false;

        return true;

    }

}
