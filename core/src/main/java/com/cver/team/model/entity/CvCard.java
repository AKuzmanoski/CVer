package com.cver.team.model.entity;

/**
 * Created by PC on 16/08/2016.
 */
public class CvCard extends DocumentImpl {
    private Person owner;

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }
}
