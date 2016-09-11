package com.cver.team.model.entity;


import com.cver.team.model.data.*;
import com.fasterxml.jackson.annotation.JsonSubTypes;

@JsonSubTypes({
        @JsonSubTypes.Type(value = Person.class),
        @JsonSubTypes.Type(value = Organization.class)
})
public class Agent extends EntityImpl {
    private String profilePictureUrl;

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }
}
