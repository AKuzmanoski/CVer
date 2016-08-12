package com.cver.team.model.data;

import com.cver.team.model.BaseEntity;
import com.cver.team.model.entity.Agent;

import java.time.LocalDateTime;

/**
 * Created by Dimitar on 8/12/2016.
 */
public class Data extends BaseEntity {

    private LocalDateTime creationDate;

    private LocalDateTime lastModified;

    private Agent owner;

    private String value;


    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getLastModified() {
        return lastModified;
    }

    public void setLastModified(LocalDateTime lastModified) {
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
