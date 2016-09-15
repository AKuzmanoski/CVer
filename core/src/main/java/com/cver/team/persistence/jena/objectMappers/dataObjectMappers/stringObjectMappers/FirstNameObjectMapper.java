package com.cver.team.persistence.jena.objectMappers.dataObjectMappers.stringObjectMappers;

import com.cver.team.model.data.string.FirstName;
import com.cver.team.persistence.jena.helper.IdentifierGenerator;
import com.cver.team.persistence.jena.namespaces.CVR;
import com.cver.team.persistence.jena.objectMappers.dataObjectMappers.DataObjectMapper;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by PC on 25/08/2016.
 */
@Component
public class FirstNameObjectMapper {
    @Autowired
    DataObjectMapper dataObjectMapper;

    public FirstName generateFirstName(Model model, Resource resource) {
        FirstName firstName = new FirstName();
        firstName = dataObjectMapper.generateData(model, resource, firstName);
        return firstName;
    }

    public void createModel(FirstName firstName, Model model) {
        if (firstName.getIdentifier() != null)
            return;
        firstName.setIdentifier(IdentifierGenerator.generateIdentifier());
        Resource resource = CVR.getResource(firstName.getIdentifier().getId());
        createModel(firstName, model, resource);
    }

    public <T extends FirstName> void createModel(FirstName firstName, Model model, Resource resource) {
        dataObjectMapper.createModel(firstName, model, resource);
    }
}
