package com.cver.team.model;

import com.cver.team.model.literal.Identifier;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PC on 27/08/2016.
 */
public class Type {
    private Identifier identifier;
    private String name;
    private List<Type> parents;

    public Type() {
        parents = new ArrayList<>();
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Identifier identifier) {
        this.identifier = identifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Type> getParents() {
        return parents;
    }

    public void setParents(List<Type> parents) {
        this.parents = parents;
    }

    public void addParent(Type parent) {
        parents.add(parent);
    }

    public void removeParent(Type parent) {
        parent.removeParent(parent);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Type)) return false;

        Type type = (Type) o;

        return getIdentifier().equals(type.getIdentifier());
    }

    @Override
    public int hashCode() {
        return getIdentifier().hashCode();
    }
}
