package com.cver.team.persistence.jena.objectMappers.dataObjectMappers.locatonObjectMappers;

import com.cver.team.model.data.Address;
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
public class AddressObjectMapper {
    @Autowired
    LocationObjectMapper locationObjectMapper;
    @Autowired
    CityObjectMapper cityObjectMapper;
    @Autowired
    CountryObjectMapper countryObjectMapper;

    public Address generateAddress(Model model, Resource resource) {
        Address address = new Address();
        address = locationObjectMapper.generateLocation(model, resource, address);

        // Street
        Statement statement = resource.getProperty(CVO.getProperty("street"));
        if(statement != null) {
            address.setStreet(statement.getObject().asLiteral().getString());
        }

        // City
        statement = resource.getProperty(CVO.getProperty("city"));
        if(statement != null) {
            address.setCity(cityObjectMapper.generateCity(model, statement.getObject().asResource()));
        }

        // Country
        statement = resource.getProperty(CVO.getProperty("country"));
        if(statement != null) {
            address.setCountry(countryObjectMapper.generateCountry(model, statement.getObject().asResource()));
        }

        return address;
    }

    public void createModel(Address address, Model model) {
        if (address.getIdentifier() != null)
            return;
        address.setIdentifier(IdentifierGenerator.generateIdentifier());
        Resource resource = CVR.getResource(address.getIdentifier().getId());
        createModel(address, model, resource);
    }

    public <T extends Address> void createModel(T address, Model model, Resource resource) {
        locationObjectMapper.createModel(address, model, resource);

        if (address.getStreet() != null) {
            Literal literal = model.createTypedLiteral(address.getStreet());
            model.add(resource, CVO.getProperty("street"), literal);
        }

        if (address.getCity() != null) {
            if (address.getCity().getIdentifier() == null)
                cityObjectMapper.createModel(address.getCity(), model);
            model.add(resource, CVO.getProperty("city"), CVR.getResource(address.getCity().getIdentifier().getId()));
        }

        if (address.getCountry() != null) {
            if (address.getCountry().getIdentifier() == null)
                countryObjectMapper.createModel(address.getCountry(), model);
            model.add(resource, CVO.getProperty("city"), CVR.getResource(address.getCountry().getIdentifier().getId()));
        }
    }
}
