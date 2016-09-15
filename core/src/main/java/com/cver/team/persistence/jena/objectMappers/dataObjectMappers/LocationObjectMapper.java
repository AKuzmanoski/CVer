package com.cver.team.persistence.jena.objectMappers.dataObjectMappers;

import com.cver.team.model.data.Address;
import com.cver.team.model.data.City;
import com.cver.team.model.data.Country;
import com.cver.team.model.data.Location;
import com.cver.team.persistence.jena.helper.IdentifierGenerator;
import com.cver.team.persistence.jena.namespaces.CVO;
import com.cver.team.persistence.jena.namespaces.CVR;
import com.cver.team.persistence.jena.objectMappers.dataObjectMappers.locatonObjectMappers.AddressObjectMapper;
import com.cver.team.persistence.jena.objectMappers.dataObjectMappers.locatonObjectMappers.CityObjectMapper;
import com.cver.team.persistence.jena.objectMappers.dataObjectMappers.locatonObjectMappers.CountryObjectMapper;
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
public class LocationObjectMapper {
    @Autowired
    DataObjectMapper dataObjectMapper;
    @Autowired
    AddressObjectMapper addressObjectMapper;
    @Autowired
    CityObjectMapper cityObjectMapper;
    @Autowired
    CountryObjectMapper countryObjectMapper;

    public <T extends Location> T generateLocation(Model model, Resource resource, T location) {
        location = dataObjectMapper.generateData(model, resource, location);

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
        if (location.getIdentifier() != null)
            return;
        if (location instanceof Address) {
            addressObjectMapper.createModel((Address)location, model);
            return;
        }
        if (location instanceof Country) {
            countryObjectMapper.createModel((Country)location, model);
            return;
        }
        if (location instanceof City) {
            cityObjectMapper.createModel((City) location, model);
            return;
        }
        location.setIdentifier(IdentifierGenerator.generateIdentifier());
        Resource resource = CVR.getResource(location.getIdentifier().getId());
        createModel(location, model, resource);
    }

    public <T extends Location> void createModel(T location, Model model, Resource resource) {
        dataObjectMapper.createModel(location, model, resource);
        if (location.getLongitude() != null) {
            Literal literal = model.createTypedLiteral(location.getLongitude());
            model.add(resource, CVO.getProperty("longitude"), literal);
        }
        if (location.getLatitude() != null) {
            Literal literal = model.createTypedLiteral(location.getLatitude());
            model.add(resource, CVO.getProperty("latitude"), literal);
        }
    }
}
