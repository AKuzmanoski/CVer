package com.cver.team.persistence.jena.objectMappers.externalResourceObjectMappers;

import com.cver.team.model.BaseEntity;
import com.cver.team.model.externalresource.ExternalResource;
import com.cver.team.model.literal.Identifier;
import com.cver.team.persistence.jena.namespaces.CVO;
import com.cver.team.persistence.jena.namespaces.CVR;
import com.cver.team.persistence.jena.objectMappers.BaseEntityObjectMapper;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;

/**
 * Created by PC on 25/08/2016.
 */
public class ExternalResourceObjectMapper {
    public static <T extends ExternalResource> T generateExternalResource(Model model, Resource resource, T externalResource) {
        externalResource = BaseEntityObjectMapper.generateBaseEntity(model, resource, externalResource);

        Statement statement = resource.getProperty(CVO.getProperty("name"));
        if(statement != null)
            externalResource.setName(statement.getObject().asLiteral().getString());

        statement = resource.getProperty(CVO.getProperty("url"));
        if(statement != null)
            externalResource.setUrl(statement.getObject().asLiteral().getString());

        return externalResource;
    }
}
