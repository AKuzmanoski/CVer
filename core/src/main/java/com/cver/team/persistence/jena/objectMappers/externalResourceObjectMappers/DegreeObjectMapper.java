package com.cver.team.persistence.jena.objectMappers.externalResourceObjectMappers;

import com.cver.team.model.externalresource.Degree;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;

/**
 * Created by PC on 25/08/2016.
 */
public class DegreeObjectMapper {
    public static Degree generateDegree(Model model, Resource resource) {
        Degree degree = new Degree();
        degree = ExternalResourceObjectMapper.generateExternalResource(model, resource, degree);
        return degree;
    }
}
