package com.cver.team.model;

import com.cver.team.model.literal.Data;
import com.cver.team.model.literal.Identifier;

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
    private byte[] profilePicture;
    private byte[] coverPicture;

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


    public byte[] getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(byte[] profilePicture) {
        this.profilePicture = profilePicture;
    }

    public byte[] getCoverPicture() {
        return coverPicture;
    }

    public void setCoverPicture(byte[] coverPicture) {
        this.coverPicture = coverPicture;
    }
}