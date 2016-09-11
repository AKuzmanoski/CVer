package com.cver.team.persistence.jena.objectMappers.dataObjectMappers.stringObjectMappers;

import com.cver.team.model.data.string.FirstName;
import com.cver.team.model.data.string.LastName;
import com.cver.team.model.literal.Identifier;
import com.cver.team.persistence.jena.helper.IdentifierGenerator;
import com.cver.team.persistence.jena.namespaces.CVR;
import com.cver.team.persistence.jena.objectMappers.dataObjectMappers.DataObjectMapper;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;

/**
 * Created by PC on 25/08/2016.
 */
public class LastNameObjectMapper {
    public static LastName generateLastName(Model model, Resource resource) {
        LastName lastName = new LastName();
        lastName = DataObjectMapper.generateData(model, resource, lastName);
        return lastName;
    }

    public static void createModel(LastName lastName, Model model) {
        if (lastName.getIdentifier() != null)
            return;
        lastName.setIdentifier(IdentifierGenerator.generateIdentifier());
        Resource resource = CVR.getResource(lastName.getIdentifier().getId());
        createModel(lastName, model, resource);
    }

    public static <T extends LastName> void createModel(T lastName, Model model, Resource resource) {
        DataObjectMapper.createModel(lastName, model, resource);
    }
}
