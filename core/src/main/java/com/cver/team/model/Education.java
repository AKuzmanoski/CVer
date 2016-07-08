package com.cver.team.model;

import java.time.LocalDate;

/**
 * Created by Dimitar on 7/5/2016.
 */
public class Education extends Experience {

    private Degree degree;

    private Period period;

    private String gdp;

    private String major;

    public Degree getDegree() {
        return degree;
    }

    public void setDegree(Degree degree) {
        this.degree = degree;
    }

    public String getGdp() {
        return gdp;
    }

    public void setGdp(String gdp) {
        this.gdp = gdp;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }
}

