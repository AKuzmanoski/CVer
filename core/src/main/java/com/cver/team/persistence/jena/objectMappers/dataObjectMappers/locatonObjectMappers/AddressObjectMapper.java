package com.cver.team.persistence.jena.objectMappers.dataObjectMappers.locatonObjectMappers;

import com.cver.team.model.data.Address;
import com.cver.team.persistence.jena.namespaces.CVO;
import com.cver.team.persistence.jena.objectMappers.dataObjectMappers.LocationObjectMapper;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;

/**
 * Created by PC on 25/08/2016.
 */
public class AddressObjectMapper {
    public static Address generateAddress(Model model, Resource resource) {
        Address address = new Address();
        address = LocationObjectMapper.generateLocation(model, resource, address);

        // Street
        Statement statement = resource.getProperty(CVO.getProperty("street"));
        if(statement != null) {
            address.setStreet(statement.getObject().asLiteral().getString());
        }

        // City
        statement = resource.getProperty(CVO.getProperty("city"));
        if(statement != null) {
            address.setCity(CityObjectMapper.generateCity(model, statement.getObject().asResource()));
        }

        // Country
        statement = resource.getProperty(CVO.getProperty("country"));
        if(statement != null) {
            address.setCountry(CountryObjectMapper.generateCountry(model, statement.getObject().asResource()));
        }

        return address;
    }
}
