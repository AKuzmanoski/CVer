package com.cver.team.model.entity;

import com.github.andrewoma.dexx.collection.List;

import java.time.LocalDate;

/**
 * Created by Dimitar on 8/12/2016.
 */
public class Organization extends Entity {

    //konkursi
   private List<Call> calls;

   private LocalDate dateOfFoundation;

    //korisnikot koj ja kreiral organizacijata
   private Person owner;

   private List<Person> members;

    public List<Person> getMembers() {
        return members;
    }

    public void setMembers(List<Person> members) {
        this.members = members;
    }

    public List<Call> getCalls() {
        return calls;
    }

    public void setCalls(List<Call> calls) {
        this.calls = calls;
    }

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
