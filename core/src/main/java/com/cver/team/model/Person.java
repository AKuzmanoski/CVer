package com.cver.team.model;

import com.cver.team.model.literal.Data;
import com.cver.team.model.literal.Identifier;

import java.util.List;

/**
 * Created by User on 3/1/2016.
 */
public class Person extends BaseEntity {
    private Identifier account;
    private List<Data<String>> firstName;
    private List<Data<String>> lastName;
    private List<Data<String>> mail;
    private List<Data<String>> sha1mail;
    private String loginEmail;
    private Role role;
    private Provider provider;

    public Role getRole() {
        return role;
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

    public String getLoginEmail() {
        return loginEmail;
    }

    public void setLoginEmail(String loginEmail) {
        this.loginEmail = loginEmail;
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

    public List<Data<String>> getFirstName() {
        return firstName;
    }

    public void setFirstName(List<Data<String>> firstName) {
        this.firstName = firstName;
    }

    public List<Data<String>> getLastName() {
        return lastName;
    }

    public void setLastName(List<Data<String>> lastName) {
        this.lastName = lastName;
    }

    public List<Data<String>> getMail() {
        return mail;
    }

    public void setMail(List<Data<String>> mail) {
        this.mail = mail;
    }

    public List<Data<String>> getSha1mail() {
        return sha1mail;
    }

    public void setSha1mail(List<Data<String>> sha1mail) {
        this.sha1mail = sha1mail;
    }


}