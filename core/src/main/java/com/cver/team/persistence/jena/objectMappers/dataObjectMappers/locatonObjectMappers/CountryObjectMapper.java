package com.cver.team.persistence.jena.objectMappers.dataObjectMappers.locatonObjectMappers;

import com.cver.team.model.data.Country;
import com.cver.team.persistence.jena.objectMappers.dataObjectMappers.LocationObjectMapper;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;

/**
 * Created by PC on 25/08/2016.
 */
public class CountryObjectMapper {
    public static Country generateCountry(Model model, Resource resource) {
        Country country = new Country();
        country = LocationObjectMapper.generateLocation(model, resource, country);

        return country;
    }

    public void createModel(Country location, Model model) {
    }
}
