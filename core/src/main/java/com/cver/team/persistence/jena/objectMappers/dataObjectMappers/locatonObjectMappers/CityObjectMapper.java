package com.cver.team.persistence.jena.objectMappers.dataObjectMappers.locatonObjectMappers;

import com.cver.team.model.data.City;
import com.cver.team.persistence.jena.namespaces.CVO;
import com.cver.team.persistence.jena.objectMappers.dataObjectMappers.LocationObjectMapper;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;

/**
 * Created by PC on 25/08/2016.
 */
public class CityObjectMapper {
    public static City generateCity(Model model, Resource resource) {
        City city = new City();
        city = LocationObjectMapper.generateLocation(model, resource, city);

        // Postal Code
        Statement statement = resource.getProperty(CVO.getProperty("postalCode"));
        if (statement != null) {
            city.setPostalCode(statement.getObject().asLiteral().toString());
        }

        return city;
    }

    public void createModel(City location, Model model) {
    }
}
