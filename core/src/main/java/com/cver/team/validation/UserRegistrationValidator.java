package com.cver.team.validation;

import com.cver.team.model.entity.Person;
import com.cver.team.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


@Component
public class UserRegistrationValidator implements Validator {


    @Autowired
    PersonService personService;

    private final int minPasswordLength = 6;
    private final int maxPasswordLength = 12;


    @Override
    public boolean supports(Class<?> aClass) {
        return Person.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        Person person = (Person) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName","firstName.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "lastName.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "email.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.empty");
        if(person.getPassword().length() < minPasswordLength || person.getPassword().length() > maxPasswordLength)
            errors.reject("password.invalid");

        if(personService.isEmailTaken(person.getEmail()))
            errors.reject("email.taken");


    }
}
