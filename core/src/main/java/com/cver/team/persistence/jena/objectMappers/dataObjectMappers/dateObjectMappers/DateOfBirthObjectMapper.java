package com.cver.team.persistence.jena.objectMappers.dataObjectMappers.dateObjectMappers;

import com.cver.team.model.data.Date;
import com.cver.team.model.data.date.DateOfBirth;
import com.cver.team.persistence.jena.helper.DateTimeConverter;
import com.cver.team.persistence.jena.namespaces.CVO;
import com.cver.team.persistence.jena.objectMappers.dataObjectMappers.DataObjectMapper;
import com.cver.team.persistence.jena.objectMappers.dataObjectMappers.DateObjectMapper;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;

/**
 * Created by PC on 25/08/2016.
 */
public class DateOfBirthObjectMapper {
    public static DateOfBirth generateDate(Model model, Resource resource) {
        DateOfBirth dateOfBirth = new DateOfBirth();
        dateOfBirth = DateObjectMapper.generateDate(model, resource, dateOfBirth);

        return dateOfBirth;
    }
}
