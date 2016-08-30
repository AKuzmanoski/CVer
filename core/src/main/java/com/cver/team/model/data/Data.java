package com.cver.team.model.data;

import com.cver.team.model.BaseEntityImpl;
import com.cver.team.model.entity.Agent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dimitar on 8/12/2016.
 */
public class Data extends BaseEntityImpl {

    private java.util.Date creationDate;

    private java.util.Date lastModified;

    private List<Agent> owners;

    private String value;

    private String name;

    public Data() {
        owners = new ArrayList<>();
    }


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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Agent> getOwners() {
        return owners;
    }

    public void setOwners(List<Agent> owners) {
        this.owners = owners;
    }

    public void addOwner(Agent agent) {
        owners.add(agent);
    }

    public void removeOwner(Agent agent) {
        owners.remove(agent);
    }
}
