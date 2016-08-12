package com.cver.team.model.entity;


/**
 * Created by Dimitar on 2/28/2016.
 */
public class Template extends EntityImpl {
    private String url;
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
