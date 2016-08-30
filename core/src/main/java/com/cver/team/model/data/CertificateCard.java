package com.cver.team.model.data;

import com.cver.team.model.entity.Agent;
import com.cver.team.model.entity.DocumentCard;

/**
 * Created by Dimitar on 7/4/2016.
 */
public class CertificateCard extends Data implements DocumentCard {

    private Agent issuer;
    private Agent recipient;
    private java.util.Date issuedDate;

    //fields from DocumentCard
    private String title;
    private Agent owner;

    // Entity fields
    private String coverPictureUrl;


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
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    public String getCoverPictureUrl() {
        return coverPictureUrl;
    }

    public void setCoverPictureUrl(String coverPictureUrl) {
        this.coverPictureUrl = coverPictureUrl;
    }

    public java.util.Date getIssuedDate() {
        return issuedDate;
    }

    public void setIssuedDate(java.util.Date issuedDate) {
        this.issuedDate = issuedDate;
    }

    @Override
    public Agent getOwner() {
        return owner;
    }

    @Override
    public void setOwner(Agent owner) {
        this.owner = owner;
    }
}

