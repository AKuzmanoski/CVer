package com.cver.team.model.data;

import java.time.LocalDateTime;

/**
 * Created by PC on 13/08/2016.
 */
public class Date extends Data {
    private java.util.Date dateTime;

    public java.util.Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(java.util.Date dateTime) {
        this.dateTime = dateTime;
    }
}
