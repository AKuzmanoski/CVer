package com.cver.team.persistence.jena.namespaces;

import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.impl.PropertyImpl;
import org.apache.jena.rdf.model.impl.ResourceImpl;

/**
 * Created by User on 3/1/2016.
 */
public class CVO {
    private static final String URI = "http://www.cver-data.com/ontology#";

    public static String getURI() {
        return URI;
    }

    public static String getURI(String name) {
        return CVO.URI + name;
    }

    public static Property getProperty(String name) {
        return new PropertyImpl(getURI(name));
    }

    public static Resource getResource(String name) {
        return new ResourceImpl(getURI(name));
    }
}
