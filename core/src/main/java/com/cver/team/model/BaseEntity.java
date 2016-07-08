package com.cver.team.model;

import com.cver.team.model.literal.Identifier;

/**
 * This class is hold just for identification of objects among their classes. The main usage of this object
 * is in relational databases.
 *
 * @author CVerTeam
 * @version 1.0
 * @since 1/17/2016
 */
public class BaseEntity {

    private Identifier identifier;

    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BaseEntity(Identifier identifier) {
        this.identifier = identifier;
    }

    public BaseEntity() {

    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Identifier identifier) {
        this.identifier = identifier;
    }
}