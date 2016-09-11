package com.cver.team.model.data;

import com.cver.team.model.entity.Association;
import com.cver.team.model.externalresource.Period;
import com.fasterxml.jackson.annotation.JsonSubTypes;


/**
 * Created by Dimitar on 7/4/2016.
 */
public class Experience extends Expertise {
    private Period period;
    private Association acquiredFrom;
    private Location location;

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    public Association getAcquiredFrom() {
        return acquiredFrom;
    }

    public void setAcquiredFrom(Association acquiredFrom) {
        this.acquiredFrom = acquiredFrom;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
