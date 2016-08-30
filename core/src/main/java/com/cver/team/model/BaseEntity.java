package com.cver.team.model;

import com.cver.team.model.externalresource.tag.Tag;
import com.cver.team.model.literal.Identifier;

import java.util.List;

/**
 * Created by PC on 17/08/2016.
 */
public interface BaseEntity {
    String getType();

    void setType(String type);

    List<Tag> getTags();

    void setTags(List<Tag> tags);

    void addTag(Tag tag);

    void removeTag(Tag tag);

    Identifier getIdentifier();

    void setIdentifier(Identifier identifier);

    String getDescription();

    void setDescription(String description);

    boolean isPublic();

    void setPublic(boolean aPublic);

    List<Type> getTypes();

    void setTypes(List<Type> types);

    void addType(Type type);

    void removeType(Type type);

}
