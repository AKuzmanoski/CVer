package com.cver.team.model.entity;

import com.cver.team.model.data.*;
import com.cver.team.model.data.string.FirstName;
import com.cver.team.model.data.string.LastName;
import com.cver.team.model.data.string.TelephoneNumber;

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
public class Cv extends DocumentImpl {

    private Date dateOfBirth;
    private FirstName firstName;
    private LastName lastName;
    private Image image;
    private List<Location> locations;
    private Person owner;
    private List<Certificate> certificates;
    private List<Email> emails;
    private List<TelephoneNumber> telephoneNumbers;
    private List<Skill> skills;
    private List<Experience> experiences;

    public Cv() {

    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public List<Email> getEmails() {
        return emails;
    }

    public void setEmails(List<Email> emails) {
        this.emails = emails;
    }

    public void setFirstName(FirstName firstName) {
        this.firstName = firstName;
    }

    public void setLastName(LastName lastName) {
        this.lastName = lastName;
    }

    public List<TelephoneNumber> getTelephoneNumbers() {
        return telephoneNumbers;
    }

    public void setTelephoneNumbers(List<TelephoneNumber> telephoneNumbers) {
        this.telephoneNumbers = telephoneNumbers;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public List<Certificate> getCertificates() {
        return certificates;
    }

    public void setCertificates(List<Certificate> certificates) {
        this.certificates = certificates;
    }

    public FirstName getFirstName() {
        return firstName;
    }

    public LastName getLastName() {
        return lastName;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
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