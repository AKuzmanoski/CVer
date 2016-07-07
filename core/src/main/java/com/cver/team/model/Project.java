package com.cver.team.model;

/**
 * Created by Dimitar on 7/7/2016.
 */
    public class Project extends Experience  {

    private String externalLink;

    private Field field;

    public String getExternalLink() {
        return externalLink;
    }

    public void setExternalLink(String externalLink) {
        this.externalLink = externalLink;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

}
