package com.cver.team.persistence.jena.objectMappers.dataObjectMappers.locatonObjectMappers;

import com.cver.team.model.data.City;
import com.cver.team.model.data.Country;
import com.cver.team.persistence.jena.helper.IdentifierGenerator;
import com.cver.team.persistence.jena.namespaces.CVO;
import com.cver.team.persistence.jena.namespaces.CVR;
import com.cver.team.persistence.jena.objectMappers.dataObjectMappers.LocationObjectMapper;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by PC on 25/08/2016.
 */
@Component
public class CityObjectMapper {
    @Autowired
    LocationObjectMapper locationObjectMapper;

    public City generateCity(Model model, Resource resource) {
        City city = new City();
        city = locationObjectMapper.generateLocation(model, resource, city);

        // Postal Code
        Statement statement = resource.getProperty(CVO.getProperty("postalCode"));
        if (statement != null) {
            city.setPostalCode(statement.getObject().asLiteral().toString());
        }

        return city;
    }

    public void createModel(City city, Model model) {
        if (city.getIdentifier() != null)
            return;
        city.setIdentifier(IdentifierGenerator.generateIdentifier());
        Resource resource = CVR.getResource(city.getIdentifier().getId());
        createModel(city, model, resource);
    }

    public <T extends City> void createModel(T city, Model model, Resource resource) {
        locationObjectMapper.createModel(city, model, resource);

        if (city.getName() != null) {
            Literal name = model.createTypedLiteral(city.getName());
            model.add(resource, CVO.getProperty("name"), name);
        }
    }
}
