package com.cver.team.model.entity;

import com.cver.team.model.data.Media;

import java.util.Date;

/**
 * Created by PC on 12/08/2016.
 */
public interface Entity {
    boolean isPublic();

    void setPublic(boolean aPublic);

    String getCoverPictureUrl();

    void setCoverPictureUrl(String cover);

    Date getCreationDate();

    void setCreationDate(Date creationDate);

    Date getLastModified();

    void setLastModified(Date lastModified);

    String getDescription();

    void setDescription(String description);

    String getName();

    void setName(String name);
}
