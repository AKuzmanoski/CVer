package com.cver.team.model;

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
public class Cv extends BaseEntity {

    private String firstName;
    private String lastName;
    private List<String> addresses;
    private String personURI;
    private List<String> emails;
    private List<Certificate> certificates;
    private List<Experience> experiences;


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



    public String getPersonURI() {
        return personURI;
    }

    public void setPersonURI(String personURI) {
        this.personURI = personURI;
    }

    public List<String> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<String> addresses) {
        this.addresses = addresses;
    }

    public List<String> getEmails() {
        return emails;
    }

    public void setEmails(List<String> emails) {
        this.emails = emails;
    }

    public List<Certificate> getCertificates() {
        return certificates;
    }

    public void setCertificates(List<Certificate> certificates) {
        this.certificates = certificates;
    }

    public List<Experience> getExperiences() {
        return experiences;
    }

    public void setExperiences(List<Experience> experiences) {
        this.experiences = experiences;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}