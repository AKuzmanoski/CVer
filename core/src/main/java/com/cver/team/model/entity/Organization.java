package com.cver.team.model.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dimitar on 8/12/2016.
 */
public class Organization extends Agent implements Association {

    public Organization() {
        owners = new ArrayList<>();
    }

    //korisnikot koj ja kreiral organizacijata
    private List<Person> owners;

    public List<Person> getOwners() {
        return owners;
    }

    public void setOwners(List<Person> owners) {
        this.owners = owners;
    }

    public void addOwner(Person person) {
        owners.add(person);
    }

    public void removeOwner(Person person) {
        owners.remove(person);
    }
}
