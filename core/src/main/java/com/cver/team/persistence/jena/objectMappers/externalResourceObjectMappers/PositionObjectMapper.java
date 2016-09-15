package com.cver.team.persistence.jena.objectMappers.externalResourceObjectMappers;

import com.cver.team.model.externalresource.Degree;
import com.cver.team.model.externalresource.Position;
import com.cver.team.persistence.jena.helper.IdentifierGenerator;
import com.cver.team.persistence.jena.namespaces.CVR;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by PC on 25/08/2016.
 */
@Component
public class PositionObjectMapper {
    @Autowired
    ExternalResourceObjectMapper externalResourceObjectMapper;

    public Position generatePosition(Model model, Resource resource) {
        Position position = new Position();
        position = externalResourceObjectMapper.generateExternalResource(model, resource, position);
        return position;
    }

    public void createModel(Position position, Model model) {
        if (position.getIdentifier() != null)
            return;
        position.setIdentifier(IdentifierGenerator.generateIdentifier());
        Resource resource = CVR.getResource(position.getIdentifier().getId());
        createModel(position, model, resource);
    }

    private <T extends Position> void createModel(T position, Model model, Resource resource) {
        externalResourceObjectMapper.createModel(position, model, resource);
    }
}
