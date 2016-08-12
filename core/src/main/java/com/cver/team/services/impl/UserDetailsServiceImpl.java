package com.cver.team.services.impl;

import com.cver.team.model.entity.Person;
import com.cver.team.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dimitar on 6/3/2016.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    PersonService personService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        System.out.println("USER DETAILS SERVICE IMPL IS CALLED !");
        Person person = personService.getPersonByLoginEmail(s);

        if(person == null)
            throw new UsernameNotFoundException("User was not found");

        SimpleGrantedAuthority role = new SimpleGrantedAuthority(person.getRole().toString());
        List<SimpleGrantedAuthority> roles = new ArrayList<SimpleGrantedAuthority>();
        roles.add(role);
        System.out.println(person);
        return new org.springframework.security.core.userdetails.User(person.getEmail(), person.getPassword(), roles);
    }
}