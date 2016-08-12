package com.cver.team.model.data;

import java.time.LocalDateTime;

/**
 * Created by PC on 13/08/2016.
 */
public class Date extends Data {
    private LocalDateTime dateTime;

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
