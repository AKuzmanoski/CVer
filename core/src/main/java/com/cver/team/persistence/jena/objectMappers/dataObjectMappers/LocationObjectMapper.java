package com.cver.team.persistence.jena.objectMappers.dataObjectMappers;

import com.cver.team.model.data.Location;
import com.cver.team.persistence.jena.namespaces.CVO;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;

/**
 * Created by PC on 25/08/2016.
 */
public class LocationObjectMapper {
    public static <T extends Location> T generateLocation(Model model, Resource resource, T location) {
        location = DataObjectMapper.generateData(model, resource, location);

        // Longitude
        Statement statement = resource.getProperty(CVO.getProperty("longitude"));
        if (statement != null)
            location.setLongitude(statement.getObject().asLiteral().getDouble());

        // Latitude
        statement = resource.getProperty(CVO.getProperty("latitude"));
        if (statement != null)
            location.setLatitude(statement.getObject().asLiteral().getDouble());

        return location;
    }

    public void createModel(Location location, Model model) {
    }
}
