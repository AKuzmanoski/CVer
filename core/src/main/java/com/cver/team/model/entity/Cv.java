package com.cver.team.model.entity;

import com.cver.team.model.data.Certificate;

import java.time.LocalDate;
import java.util.List;

/**
 * This is the class which drives the whole logic of this system. Its name tells that it represents a personal
 * resume with all its characteristics inside. It will hold personal information like name of the owner, address,
 * telephone number, its skills, education status and previous ,and current work.
 * Also it will hold many other connections with other Cvs and companies.
 *
 * @author CVerTeam
 * @version 1.0
 * @since 1/17/2016
 */
public class Cv extends Document {

    private LocalDate dateOfBirth;
    private String firstName;
    private String lastName;
    private List<String> addresses;
    private Person owner;
    private List<Certificate> certificates;



    public Cv(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }



    public Cv() {

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public List<String> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<String> addresses) {
        this.addresses = addresses;
    }


    public List<Certificate> getCertificates() {
        return certificates;
    }

    public void setCertificates(List<Certificate> certificates) {
        this.certificates = certificates;
    }


    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}