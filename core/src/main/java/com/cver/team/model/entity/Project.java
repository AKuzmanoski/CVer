package com.cver.team.model.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dimitar on 7/7/2016.
 */
    public class Project extends EntityImpl implements Association {
    private List<Agent> owners;
    private String externalLink;

    public Project() {
        owners = new ArrayList<>();
    }

    public String getExternalLink() {
        return externalLink;
    }

    public void setExternalLink(String externalLink) {
        this.externalLink = externalLink;
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
