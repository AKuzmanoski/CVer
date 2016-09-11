package com.cver.team.persistence.jena.objectMappers.dataObjectMappers.stringObjectMappers;

import com.cver.team.model.data.string.ValueProposition;
import com.cver.team.persistence.jena.helper.IdentifierGenerator;
import com.cver.team.persistence.jena.namespaces.CVR;
import com.cver.team.persistence.jena.objectMappers.dataObjectMappers.DataObjectMapper;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;

/**
 * Created by PC on 25/08/2016.
 */
public class ValuePropositionObjectMapper {
    public static ValueProposition generateValueProposition(Model model, Resource resource) {
        ValueProposition valueProposition = new ValueProposition();

       valueProposition = DataObjectMapper.generateData(model, resource, valueProposition);

        return valueProposition;
    }

    public static void createModel(ValueProposition valueProposition, Model model) {
        if (valueProposition.getIdentifier() != null)
            return;
        valueProposition.setIdentifier(IdentifierGenerator.generateIdentifier());
        Resource resource = CVR.getResource(valueProposition.getIdentifier().getId());
        createModel(valueProposition, model, resource);
    }

    public static <T extends ValueProposition> void createModel(T valueProposition, Model model, Resource resource) {
        DataObjectMapper.createModel(valueProposition, model, resource);
    }
}
