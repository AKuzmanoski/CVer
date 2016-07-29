package com.cver.team.persistence.jena.namespaces;

/**
 * Created by User on 3/1/2016.
 */
public class CVG {
    private static final String URI = "http://www.cver-data.com/graph#";

    public static String getURI() {
        return URI;
    }

    public static String getUri(String name) {
        return CVG.URI + name;
    }
}
