package com.cver.team.model.data;

import com.cver.team.model.BaseEntity;
import com.cver.team.model.entity.Agent;

/**
 * Created by Dimitar on 8/12/2016.
 */
public class Data extends BaseEntity {

    private java.util.Date creationDate;

    private java.util.Date lastModified;

    private Agent owner;

    private String value;


    public java.util.Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(java.util.Date creationDate) {
        this.creationDate = creationDate;
    }

    public java.util.Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(java.util.Date lastModified) {
        this.lastModified = lastModified;
    }

    public Agent getOwner() {
        return owner;
    }

    public void setOwner(Agent owner) {
        this.owner = owner;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
