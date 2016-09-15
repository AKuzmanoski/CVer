package com.cver.team.persistence.jena.objectMappers.dataObjectMappers.stringObjectMappers;

import com.cver.team.model.data.string.FirstName;
import com.cver.team.model.data.string.LastName;
import com.cver.team.model.literal.Identifier;
import com.cver.team.persistence.jena.helper.IdentifierGenerator;
import com.cver.team.persistence.jena.namespaces.CVR;
import com.cver.team.persistence.jena.objectMappers.dataObjectMappers.DataObjectMapper;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.apache.lucene.store.DataOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by PC on 25/08/2016.
 */
@Component
public class LastNameObjectMapper {
    @Autowired
    DataObjectMapper dataObjectMapper;

    public LastName generateLastName(Model model, Resource resource) {
        LastName lastName = new LastName();
        lastName = dataObjectMapper.generateData(model, resource, lastName);
        return lastName;
    }

    public void createModel(LastName lastName, Model model) {
        if (lastName.getIdentifier() != null)
            return;
        lastName.setIdentifier(IdentifierGenerator.generateIdentifier());
        Resource resource = CVR.getResource(lastName.getIdentifier().getId());
        createModel(lastName, model, resource);
    }

    public <T extends LastName> void createModel(T lastName, Model model, Resource resource) {
        dataObjectMapper.createModel(lastName, model, resource);
    }
}
