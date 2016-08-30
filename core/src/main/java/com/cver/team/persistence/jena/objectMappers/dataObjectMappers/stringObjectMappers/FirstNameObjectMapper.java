package com.cver.team.persistence.jena.objectMappers.dataObjectMappers.stringObjectMappers;

import com.cver.team.model.data.string.FirstName;
import com.cver.team.persistence.jena.objectMappers.dataObjectMappers.DataObjectMapper;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;

/**
 * Created by PC on 25/08/2016.
 */
public class FirstNameObjectMapper {
    public static FirstName generateFirstName(Model model, Resource resource) {
        FirstName firstName = new FirstName();
        firstName = DataObjectMapper.generateData(model, resource, firstName);
        return firstName;
    }
}
