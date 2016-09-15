package com.cver.team.persistence.jena.objectMappers.dataObjectMappers.locatonObjectMappers;

import com.cver.team.model.data.Country;
import com.cver.team.persistence.jena.helper.IdentifierGenerator;
import com.cver.team.persistence.jena.namespaces.CVO;
import com.cver.team.persistence.jena.namespaces.CVR;
import com.cver.team.persistence.jena.objectMappers.dataObjectMappers.LocationObjectMapper;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by PC on 25/08/2016.
 */
@Component
public class CountryObjectMapper {
    @Autowired
    LocationObjectMapper locationObjectMapper;

    public Country generateCountry(Model model, Resource resource) {
        Country country = new Country();
        country = locationObjectMapper.generateLocation(model, resource, country);

        return country;
    }

    public void createModel(Country country, Model model) {
        if (country.getIdentifier() != null)
            return;
        country.setIdentifier(IdentifierGenerator.generateIdentifier());
        Resource resource = CVR.getResource(country.getIdentifier().getId());
        createModel(country, model, resource);
    }

    public <T extends Country> void createModel(T country, Model model, Resource resource) {
        locationObjectMapper.createModel(country, model, resource);

        if (country.getName() != null) {
            Literal name = model.createTypedLiteral(country.getName());
            model.add(resource, CVO.getProperty("name"), name);
        }
    }
}
