package com.cver.team.model.data;

import com.cver.team.model.entity.Template;
import com.cver.team.model.entity.Agent;
import com.cver.team.model.entity.Entity;
import com.cver.team.model.tag.CertificateTag;

import java.util.List;

/**
 * Created by Dimitar on 7/4/2016.
 */
public class Certificate extends Entity {

    private CertificateTag certificateTag;

    private Agent issuer;

    private Agent recipient;

    //fields from Document

    private Address address;

    private City city;

    private Country country;

    private List<Data> data;

    private List<Experience> experiences;

    private Email email;

    private String imageURL;

    private String telephoneNumber;

    private Template template;

    private String videoURL;

    private String valueProposition;


    public CertificateTag getCertificateTag() {
        return certificateTag;
    }

    public void setCertificateTag(CertificateTag certificateTag) {
        this.certificateTag = certificateTag;
    }

    public Agent getIssuer() {
        return issuer;
    }

    public void setIssuer(Agent issuer) {
        this.issuer = issuer;
    }

    public Agent getRecipient() {
        return recipient;
    }

    public void setRecipient(Agent recipient) {
        this.recipient = recipient;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public List<Experience> getExperiences() {
        return experiences;
    }

    public void setExperiences(List<Experience> experiences) {
        this.experiences = experiences;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getValueProposition() {
        return valueProposition;
    }

    public void setValueProposition(String valueProposition) {
        this.valueProposition = valueProposition;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }

    public Template getTemplate() {
        return template;
    }

    public void setTemplate(Template template) {
        this.template = template;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }
}

