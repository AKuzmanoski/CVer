package com.cver.team.model.entity;

/**
 * Created by Dimitar on 8/12/2016.
 */
public class Call extends Entity {

    private Agent owner;

    public Agent getOwner() {
        return owner;
    }

    public void setOwner(Agent owner) {
        this.owner = owner;
    }
}
