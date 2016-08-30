package com.cver.team.persistence.jena.objectMappers.externalResourceObjectMappers;

import com.cver.team.model.externalresource.Position;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;

/**
 * Created by PC on 25/08/2016.
 */
public class PositionObjectMapper {
    public static Position generatePosition(Model model, Resource resource) {
        Position position = new Position();
        position = ExternalResourceObjectMapper.generateExternalResource(model, resource, position);
        return position;
    }
}
