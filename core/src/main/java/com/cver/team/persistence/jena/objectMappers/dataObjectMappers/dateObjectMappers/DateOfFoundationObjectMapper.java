package com.cver.team.persistence.jena.objectMappers.dataObjectMappers.dateObjectMappers;

import com.cver.team.model.data.date.DateOfFoundation;
import com.cver.team.persistence.jena.objectMappers.dataObjectMappers.DateObjectMapper;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;

/**
 * Created by PC on 25/08/2016.
 */
public class DateOfFoundationObjectMapper {
    public static DateOfFoundation generateDate(Model model, Resource resource) {
        DateOfFoundation dateOfFoundation = new DateOfFoundation();
        dateOfFoundation = DateObjectMapper.generateDate(model, resource, dateOfFoundation);

        return dateOfFoundation;
    }
}
