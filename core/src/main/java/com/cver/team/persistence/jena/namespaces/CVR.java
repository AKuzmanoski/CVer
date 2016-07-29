package com.cver.team.persistence.jena.namespaces;

import com.cver.team.persistence.helper.URIMaker;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.impl.ResourceImpl;

/**
 * Created by User on 3/1/2016.
 */
public class CVR {
    private static final String URI = "http://www.cver-data.com/resource#";

    public static String getURI() {
        return URI;
    }

    public static String getURI(String name) {
        return CVR.URI + name;
    }

    public static Resource getResource(String name) {
        return new ResourceImpl(getURI(name));
    }

    public static String getId(String URI) {
        return URI.substring(URI.indexOf('#') + 1);
    }

    public static String generateURI() { return CVR.URI + URIMaker.generateUri(); }
}
