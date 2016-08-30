package com.cver.team.model.entity;

import com.cver.team.model.data.*;
import com.cver.team.model.data.date.DateOfBirth;
import com.cver.team.model.data.string.FirstName;
import com.cver.team.model.data.string.LastName;
import com.cver.team.model.data.string.TelephoneNumber;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the class which drives the whole logic of this system. Its name tells that it represents a personal
 * resume with all its characteristics inside. It will hold personal information like name of the owner, address,
 * telephone number, its skills, educations status and previous ,and current work.
 * Also it will hold many other connections with other Cvs and companies.
 *
 * @author CVerTeam
 * @version 1.0
 * @since 1/17/2016
 */
public class CV extends DocumentImpl {

    private DateOfBirth dateOfBirth;
    private FirstName firstName;
    private LastName lastName;
    private Image image;
    private List<Location> locations;
    private List<Certificate> certificates;
    private List<Email> emails;
    private List<TelephoneNumber> telephoneNumbers;
    private List<Skill> skills;
    private List<Experience> experiences;
    private List<ProjectExperience> projectExperiences;
    private List<WorkExperience> workExperiences;
    private List<EducationalExperience> educations;

    public CV() {
        locations = new ArrayList<>();
        certificates = new ArrayList<>();
        emails = new ArrayList<>();
        telephoneNumbers = new ArrayList<>();
        skills = new ArrayList<>();
        experiences = new ArrayList<>();
        workExperiences = new ArrayList<>();
        projectExperiences = new ArrayList<>();
        educations = new ArrayList<>();
    }

    public void addLocation(Location location) {
        locations.add(location);
    }

    public void addCertificate(Certificate certificate) {
        certificates.add(certificate);
    }

    public void addEmail(Email email) {
        emails.add(email);
    }

    public void addTelephoneNumber(TelephoneNumber telephoneNumber) {
        telephoneNumbers.add(telephoneNumber);
    }

    public void addSkill(Skill skill) {
        skills.add(skill);
    }

    public void addExperience(Experience experience) {
        experiences.add(experience);
    }

    public void addEducation(EducationalExperience experience) {
        educations.add(experience);
    }

    public void addWorkExperience(WorkExperience experience) {
        workExperiences.add(experience);
    }

    public void addProjectExperience(ProjectExperience experience) {
        projectExperiences.add(experience);
    }

    public DateOfBirth getDateOfBirth() {
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

    public void setDateOfBirth(DateOfBirth dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
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

    public List<EducationalExperience> getEducations() {
        return educations;
    }

    public void setEducations(List<EducationalExperience> educations) {
        this.educations = educations;
    }

    public List<ProjectExperience> getProjectExperiences() {
        return projectExperiences;
    }

    public void setProjectExperiences(List<ProjectExperience> projectExperiences) {
        this.projectExperiences = projectExperiences;
    }

    public List<WorkExperience> getWorkExperiences() {
        return workExperiences;
    }

    public void setWorkExperiences(List<WorkExperience> workExperiences) {
        this.workExperiences = workExperiences;
    }
}