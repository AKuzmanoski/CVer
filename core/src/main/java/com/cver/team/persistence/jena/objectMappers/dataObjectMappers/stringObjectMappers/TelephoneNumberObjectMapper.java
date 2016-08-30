package com.cver.team.persistence.jena.objectMappers.dataObjectMappers.stringObjectMappers;

import com.cver.team.model.data.string.LastName;
import com.cver.team.model.data.string.TelephoneNumber;
import com.cver.team.persistence.jena.objectMappers.dataObjectMappers.DataObjectMapper;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;

/**
 * Created by PC on 25/08/2016.
 */
public class TelephoneNumberObjectMapper {
    public static TelephoneNumber generateTelephoneNumber(Model model, Resource resource) {
        TelephoneNumber telephoneNumber = new TelephoneNumber();
        telephoneNumber = DataObjectMapper.generateData(model, resource, telephoneNumber);
        return telephoneNumber;
    }
}
