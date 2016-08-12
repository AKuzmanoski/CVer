package com.cver.team.model.entity;

import com.cver.team.model.data.Media;

import java.time.LocalDateTime;

/**
 * Created by PC on 12/08/2016.
 */
public interface Entity {
    boolean isPublic();

    void setPublic(boolean aPublic);

    String getCoverPictureUrl();

    void setCoverPictureUrl(String cover);

    LocalDateTime getCreationDate();

    void setCreationDate(LocalDateTime creationDate);

    LocalDateTime getLastModified();

    void setLastModified(LocalDateTime lastModified);

    String getDescription();

    void setDescription(String description);

    String getName();

    void setName(String name);
}
