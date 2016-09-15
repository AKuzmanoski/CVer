package com.cver.team.model;

import com.cver.team.model.literal.Identifier;
import com.cver.team.model.externalresource.tag.Tag;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is hold just for identification of objects among their classes. The main usage of this object
 * is in relational databases.
 *
 * @author CVerTeam
 * @version 1.0
 * @since 1/17/2016
 */
public class BaseEntityImpl implements Serializable, BaseEntity {
    private Identifier identifier;
    private List<Tag> tags;
    private String description;
    private boolean isPublic;
    private List<Type> types;


    public BaseEntityImpl(Identifier identifier) {
        this.tags = new ArrayList<>();
        this.identifier = identifier;
    }

    public List<Type> getTypes() {
        return types;
    }

    public void setTypes(List<Type> types) {
        this.types = types;
    }

    public BaseEntityImpl() {
        this.tags = new ArrayList<>();
        types = new ArrayList<>();
    }



    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Identifier identifier) {
        this.identifier = identifier;
    }

    @Override
    public void addTag(Tag tag) {
        tags.add(tag);
    }

    @Override
    public void removeTag(Tag tag) {
        tags.remove(tag);
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "BaseEntityImpl{" +
                "identifier=" + identifier +
                '}';
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    @Override
    public void addType(Type type) {
        types.add(type);
    }

    @Override
    public void removeType(Type type) {
        types.remove(type);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseEntityImpl)) return false;

        BaseEntityImpl that = (BaseEntityImpl) o;

        return getIdentifier() != null ? getIdentifier().equals(that.getIdentifier()) : that.getIdentifier() == null;

    }

    @Override
    public int hashCode() {
        return getIdentifier() != null ? getIdentifier().hashCode() : 0;
    }
}