package com.cver.team.persistence.jena.namespaces;

import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.impl.PropertyImpl;
import org.apache.jena.rdf.model.impl.ResourceImpl;

/**
 * Created by PC on 17/08/2016.
 */
public class LUC {
    private static final String URI = "http://www.ontotext.com/connectors/lucene#";

    public static String getURI() {
        return URI;
    }

    public static String getURI(String name) {
        return URI + name;
    }

    public static Property getProperty(String name) {
        return new PropertyImpl(getURI(name));
    }

    public static Resource getResource(String name) {
        return new ResourceImpl(getURI(name));
    }
}
