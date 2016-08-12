package com.cver.team.services.impl;


import com.cver.team.model.*;
import com.cver.team.model.entity.Person;
import com.cver.team.persistence.AddressRepository;
import com.cver.team.persistence.CertificateRepository;
import com.cver.team.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class UserInit {

    @Autowired
    PersonService personService;

    @Autowired
    AddressRepository addressRepositoryJena;

    @Autowired
    CertificateRepository certificateRepository;

    @PostConstruct
    public void init() {

        if(!personService.isEmailTaken("dimitar@wow.com")) {
            Person person = new Person();
            person.setEmail("dimitar@wow.com");
            person.setProvider(Provider.LINKED);
            person.setRole(Role.ROLE_USER);
            person.setFirstName("TestIme");
            person.setLastName("TestPrezime");
            personService.saveNewPerson(person);
        }

//        if(!personService.isEmailTaken("ile@local.com")) {
//            Person person2 = new Person();
//            person2.setEmail("ile@local.com");
//            person2.setFirstName("Ile");
//            person2.setLastName("Ileski");
//            person2.setProvider(Provider.LOCAL);
//            person2.setRole(Role.ROLE_USER);
//
//            personService.saveNewPerson(person2);
//        }
//
//        Person person = personService.getPersonByLoginEmailWithoutPassword("ile@local.com");
//        Person dime = personService.getPersonByLoginEmailWithoutPassword("dimitar@local.com");
//
//        System.out.println("----------------------------------------");
//        System.out.println(person.getPassword());
//        System.out.println(person.getEmail());
//        System.out.println(person.getIdentifier().getURI());
//        System.out.println(person.getProvider());
//        System.out.println(person.getRole());
//        System.out.println(person.getFirstName());
//        System.out.println(person.getLastName());
//        System.out.println("----------------------------------------");
//
//        Certificate certificate = new Certificate();
//        certificate.setIssuerURI(dime.getIdentifier().getURI());
//        certificate.setDescription("This is the best certificate evah!");
//        certificate.setRecipientURI(person.getIdentifier().getURI());
//        //certificateRepository.saveNewCertificate(certificate);
////
////
////        Address address = new Address();
////        address.setCountry("Serbia");
////        address.setStreet("Beogradska 123");
////       // addressRepositoryJena.saveNewAddress(address,person);
////
////        List<Address> addresses = addressRepositoryJena.getAddressesForPerson(person);
////
////        for(Address ad : addresses) {
////            if(ad.getCountry() != null && ad.getCountry().equals("test@en")) {
////                System.out.println("I AM IN DELETE !");
////                addressRepositoryJena.deleteAddresss(ad);
////            }
////            System.out.println(ad.getIdentifier().getURI() + " " +ad.getCity()+" "+ad.getCountry()+" "+ad.getStreet());
////        }


    }
}
