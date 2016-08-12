package com.cver.team.model.entity;

import com.github.andrewoma.dexx.collection.List;

import java.time.LocalDate;

/**
 * Created by Dimitar on 8/12/2016.
 */
public class Organization extends Agent implements Association {
   private LocalDate dateOfFoundation;

    //korisnikot koj ja kreiral organizacijata
   private Person owner;

    public LocalDate getDateOfFoundation() {
        return dateOfFoundation;
    }

    public void setDateOfFoundation(LocalDate dateOfFoundation) {
        this.dateOfFoundation = dateOfFoundation;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }
}
