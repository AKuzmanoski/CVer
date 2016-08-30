package com.cver.team.model.entity;

/**
 * Created by Dimitar on 8/12/2016.
 */
public class Call extends EntityImpl {

    private Organization owner;

    public Organization getOwner() {
        return owner;
    }

    public void setOwner(Organization owner) {
        this.owner = owner;
    }
}
