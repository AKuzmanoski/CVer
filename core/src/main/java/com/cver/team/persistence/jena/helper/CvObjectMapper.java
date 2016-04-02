package com.cver.team.persistence.jena.helper;

import com.cver.team.model.Cv;
import com.cver.team.model.helper.CvNullable;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.NodeIterator;
import org.apache.jena.rdf.model.ResIterator;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.impl.PropertyImpl;
import org.apache.jena.sparql.vocabulary.FOAF;
import org.apache.jena.vocabulary.RDF;

/**
 * Created by User on 3/1/2016.
 */
public class CvObjectMapper {
    public static Cv generateCv(Model model) {
        ResIterator iterator = model.listResourcesWithProperty(RDF.type, FOAF.Person);
        if (iterator.hasNext())
            return generateCv(model, iterator.nextResource());
        else
            return new CvNullable();
    }

    public static Cv generateCv(Model model, Resource resource) {
        Cv cv = new Cv();
        NodeIterator iterator = model.listObjectsOfProperty(resource, FOAF.givenname);
        while (iterator.hasNext()) {
            cv.setFirstName(iterator.next().toString());
        }
        iterator = model.listObjectsOfProperty(resource, FOAF.family_name);
        while (iterator.hasNext()) {
            cv.setLastName(iterator.next().toString());
        }
        iterator = model.listObjectsOfProperty(resource, FOAF.holdsAccount);
        while (iterator.hasNext()) {
            cv.setAccount(iterator.next().toString());
        }
        return cv;
    }
}
