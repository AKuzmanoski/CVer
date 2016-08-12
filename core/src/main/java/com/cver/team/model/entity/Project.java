package com.cver.team.model.entity;

/**
 * Created by Dimitar on 7/7/2016.
 */
    public class Project extends EntityImpl implements Association {

    private String externalLink;

    public String getExternalLink() {
        return externalLink;
    }

    public void setExternalLink(String externalLink) {
        this.externalLink = externalLink;
    }

}
