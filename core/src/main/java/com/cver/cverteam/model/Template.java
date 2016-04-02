package com.cver.cverteam.model;

/**
 * Created by Dimitar on 2/28/2016.
 */
public class Template extends BaseEntity {
 private String name;
 private String location;
 private String format;

    public Template(String name, String location, String format) {
        this.name = name;
        this.location = location;
        this.format = format;
    }

    public Template() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
