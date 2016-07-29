package com.cver.team.persistence.jena.queries;

/**
 * Created by User on 7/28/2016.
 */
public class GraphQuery {
    public String name;
    public String body;

    public GraphQuery() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}