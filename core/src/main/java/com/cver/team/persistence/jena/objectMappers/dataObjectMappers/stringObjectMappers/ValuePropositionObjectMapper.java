package com.cver.team.persistence.jena.objectMappers.dataObjectMappers.stringObjectMappers;

import com.cver.team.model.data.string.ValueProposition;
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
public class ValuePropositionObjectMapper {
    @Autowired
    DataObjectMapper dataObjectMapper;

    public ValueProposition generateValueProposition(Model model, Resource resource) {
        ValueProposition valueProposition = new ValueProposition();

       valueProposition = dataObjectMapper.generateData(model, resource, valueProposition);

        return valueProposition;
    }

    public void createModel(ValueProposition valueProposition, Model model) {
        if (valueProposition.getIdentifier() != null)
            return;
        valueProposition.setIdentifier(IdentifierGenerator.generateIdentifier());
        Resource resource = CVR.getResource(valueProposition.getIdentifier().getId());
        createModel(valueProposition, model, resource);
    }

    public <T extends ValueProposition> void createModel(T valueProposition, Model model, Resource resource) {
        dataObjectMapper.createModel(valueProposition, model, resource);
    }
}
