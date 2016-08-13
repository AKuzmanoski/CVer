package com.cver.team.model.data;

import com.cver.team.model.data.string.ValueProposition;
import com.cver.team.model.entity.Document;
import com.cver.team.model.entity.Template;
import com.cver.team.model.entity.Agent;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Dimitar on 7/4/2016.
 */
public class Certificate extends Data implements Document {

    private Agent issuer;
    private Agent recipient;
    private java.util.Date issuedDate;

    //fields from Document
    private Template template;
    private String title;
    private ValueProposition valueProposition;
    private Expertise forExpertise;

    // Entity fields
    private String coverPictureUrl;
    private String description;
    private String name;
    private boolean isPublic;


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

    @Override
    public Template getTemplate() {
        return template;
    }

    @Override
    public void setTemplate(Template template) {
        this.template = template;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public ValueProposition getValueProposition() {
        return valueProposition;
    }

    @Override
    public void setValueProposition(ValueProposition valueProposition) {
        this.valueProposition = valueProposition;
    }

    public Expertise getForExpertise() {
        return forExpertise;
    }

    public void setForExpertise(Expertise forExpertise) {
        this.forExpertise = forExpertise;
    }

    public String getCoverPictureUrl() {
        return coverPictureUrl;
    }

    public void setCoverPictureUrl(String coverPictureUrl) {
        this.coverPictureUrl = coverPictureUrl;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean isPublic() {
        return isPublic;
    }

    @Override
    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public java.util.Date getIssuedDate() {
        return issuedDate;
    }

    public void setIssuedDate(java.util.Date issuedDate) {
        this.issuedDate = issuedDate;
    }
}

