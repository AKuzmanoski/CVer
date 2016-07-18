package com.cver.team.model;

import com.cver.team.model.literal.Data;
import com.cver.team.model.literal.Identifier;

import java.util.Arrays;
import java.util.List;

/**
 * Created by User on 3/1/2016.
 */
public class Person extends BaseEntity {
    private Identifier account;
    private String firstName;
    private String lastName;
    private String email;
    private Role role;
    private Provider provider;
    private String profilePictureURL;
    private String coverPictureURL;

    public Role getRole() { return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String password;

    public Identifier getAccount() {
        return account;
    }

    public void setAccount(Identifier account) {
        this.account = account;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    @Override
    public String toString() {
        return "Person{" +
                "account=" + account +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                ", provider=" + provider +
                ", password='" + password + '\'' +
                '}';
    }
}