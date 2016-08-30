package com.cver.team.model.entity;

/**
 * Created by PC on 12/08/2016.
 */
public class DocumentCardImpl extends EntityImpl implements DocumentCard {
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private Agent owner;

    public Agent getOwner() {
        return owner;
    }

    public void setOwner(Agent owner) {
        this.owner = owner;
    }
}
