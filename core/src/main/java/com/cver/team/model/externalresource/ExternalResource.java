package com.cver.team.model.externalresource;

import com.cver.team.model.BaseEntity;

/**
 * Created by Dimitar on 8/12/2016.
 */
public class ExternalResource extends BaseEntity {

   private String name;

   private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
