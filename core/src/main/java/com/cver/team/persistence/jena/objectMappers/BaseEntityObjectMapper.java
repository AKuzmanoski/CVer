package com.cver.team.persistence.jena.objectMappers;

import com.cver.team.model.BaseEntity;
import com.cver.team.model.literal.Identifier;
import com.cver.team.persistence.jena.namespaces.CVR;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;

/**
 * Created by PC on 17/08/2016.
 */
public class BaseEntityObjectMapper {
    public static <T extends BaseEntity> T generateBaseEntity(Model model, Resource resource, T baseEntity) {
        // URI
        String uri = resource.getURI();
        Identifier identifier = new Identifier();
        identifier.setURI(uri);
        identifier.setId(CVR.getId(uri));
        baseEntity.setIdentifier(identifier);

        return baseEntity;
    }
}
