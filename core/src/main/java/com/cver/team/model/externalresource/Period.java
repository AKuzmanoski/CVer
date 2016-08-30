package com.cver.team.model.externalresource;

import java.util.Date;

/**
 * Created by Dimitar on 7/7/2016.
 */
public class Period extends ExternalResource {

    private Date startDate;

    private Date endDate;

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

}
