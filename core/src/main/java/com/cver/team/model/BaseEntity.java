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
    private String type;


    public BaseEntity(Identifier identifier) {
        this.identifier = identifier;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BaseEntity() {

    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Identifier identifier) {
        this.identifier = identifier;
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "identifier=" + identifier +
                ", type='" + type + '\'' +
                '}';
    }
}