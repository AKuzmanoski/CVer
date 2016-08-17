package com.cver.team.model;

import com.cver.team.model.externalresource.tag.Tag;
import com.cver.team.model.literal.Identifier;

/**
 * Created by PC on 17/08/2016.
 */
public interface BaseEntity {
    String getType();

    void setType(String type);

    Tag getTag();

    void setTag(Tag tag);

    Identifier getIdentifier();

    void setIdentifier(Identifier identifier);
}
