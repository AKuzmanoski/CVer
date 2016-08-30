package com.cver.team.model.entity;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dimitar on 2/28/2016.
 */
public class Template extends EntityImpl {
    private List<Agent> owners;
    private String url;
    private String value;

    public Template() {
        owners = new ArrayList<>();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
