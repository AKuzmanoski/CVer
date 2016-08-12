package com.cver.team.model.data;

import com.cver.team.model.externalresource.Degree;

/**
 * Created by Dimitar on 8/12/2016.
 */
public class EducationalExperience extends Experience {

    private Degree degree;

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

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

}
