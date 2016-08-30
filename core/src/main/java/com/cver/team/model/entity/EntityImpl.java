package com.cver.team.model.entity;

import com.cver.team.model.BaseEntityImpl;

import java.util.Date;

/**
 * Created by Dimitar on 8/12/2016.
 */
public class EntityImpl extends BaseEntityImpl implements Entity {

    private String coverPictureUrl;

    private Date creationDate;

    private Date lastModified;

    private String name;

    public String getCoverPictureUrl() {
        return coverPictureUrl;
    }

    public void setCoverPictureUrl(String coverPictureUrl) {
        this.coverPictureUrl = coverPictureUrl;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
