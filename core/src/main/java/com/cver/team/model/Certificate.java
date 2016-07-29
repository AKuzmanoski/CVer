package com.cver.team.model;

/**
 * Created by Dimitar on 7/4/2016.
 */
public class Certificate extends Entity {

    private String certificateType;

    private String issuerURI;

    private String recipientURI;

    public String getCertificateType() {
        return certificateType;
    }

    public void setCertificateType(String certificateType) {
        this.certificateType = certificateType;
    }

    public String getIssuerURI() {
        return issuerURI;
    }

    public void setIssuerURI(String issuerURI) {
        this.issuerURI = issuerURI;
    }

    public String getRecipientURI() {
        return recipientURI;
    }

    public void setRecipientURI(String recipientURI) {
        this.recipientURI = recipientURI;
    }
}
