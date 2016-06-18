package com.cver.team.services.impl;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dimitar on 6/3/2016.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        System.out.println("USER DETAILS SERVICE IMPL IS CALLED !");
        SimpleGrantedAuthority role = new SimpleGrantedAuthority("ROLE_USER");
        List<SimpleGrantedAuthority> roles = new ArrayList<SimpleGrantedAuthority>();
        roles.add(role);
        return new org.springframework.security.core.userdetails.User("test@test.com", "test123", roles);
    }
}