package com.cver.team.persistence.jena.helper;

import com.cver.team.model.Cv;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ResIterator;
import org.apache.jena.sparql.vocabulary.FOAF;
import org.apache.jena.vocabulary.RDF;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 3/1/2016.
 */
public class CvsObjectMapper {
    public static List<Cv> generateCvs(Model model) {
        List<Cv> cvs = new ArrayList<>();
        ResIterator iterator = model.listResourcesWithProperty(RDF.type, FOAF.Person);
        while (iterator.hasNext())
            cvs.add(CvObjectMapper.generateCv(model, iterator.nextResource()));
        return cvs;
    }
}
