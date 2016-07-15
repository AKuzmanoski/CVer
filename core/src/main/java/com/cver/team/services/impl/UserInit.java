package com.cver.team.services.impl;


import com.cver.team.model.Address;
import com.cver.team.model.Person;
import com.cver.team.model.Provider;
import com.cver.team.model.Role;
import com.cver.team.persistence.jena.AddressRepositoryJena;
import com.cver.team.services.PersonService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class UserInit {

    @Autowired
    PersonService personService;

    @Autowired
    AddressRepositoryJena addressRepositoryJena;

    @PostConstruct
    public void init() {

        if(!personService.isEmailTaken("dimitar@linkedIn.com")) {
            Person person = new Person();
            person.setEmail("dimitar@linkedIn.com");
            person.setProvider(Provider.LINKED);
            person.setRole(Role.ROLE_USER);

            personService.saveNewPerson(person);
        }

        if(!personService.isEmailTaken("dimitar@local.com")) {
            Person person2 = new Person();
            person2.setEmail("dimitar@local.com");
            person2.setPassword("password");
            person2.setFirstName("Dimitar");
            person2.setLastName("Spasovski");
            person2.setProvider(Provider.LOCAL);
            person2.setRole(Role.ROLE_USER);

            personService.saveNewPerson(person2);
        }

        Person person = personService.getPersonByLoginEmailWithoutPassword("dimitar@local.com");
//        System.out.println("----------------------------------------");
////        System.out.println(person.getPassword());
//        System.out.println(person.getEmail());
//        System.out.println(person.getIdentifier().getId());
//        System.out.println(person.getProvider());
//        System.out.println(person.getRole());
//        System.out.println("----------------------------------------");


//        Address address = new Address();
//        address.setCity("Tetovo");
//        address.setStreet("Tetovska 123");
//       // addressRepositoryJena.saveNewAddress(address,person);
//
//        List<Address> addresses = addressRepositoryJena.getAddressesForPerson(person);
//
//        for(Address ad : addresses) {
//            System.out.println(ad.getCity()+" "+ad.getCountry()+" "+ad.getStreet());
//        }


    }
}
