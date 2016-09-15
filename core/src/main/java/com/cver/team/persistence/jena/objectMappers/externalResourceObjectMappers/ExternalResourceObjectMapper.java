package com.cver.team.persistence.jena.objectMappers.externalResourceObjectMappers;

import com.cver.team.model.BaseEntity;
import com.cver.team.model.externalresource.ExternalResource;
import com.cver.team.model.externalresource.SkillResource;
import com.cver.team.model.literal.Identifier;
import com.cver.team.persistence.jena.namespaces.CVO;
import com.cver.team.persistence.jena.namespaces.CVR;
import com.cver.team.persistence.jena.objectMappers.BaseEntityObjectMapper;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by PC on 25/08/2016.
 */
@Component
public class ExternalResourceObjectMapper {
    @Autowired
    BaseEntityObjectMapper baseEntityObjectMapper;

    public <T extends ExternalResource> T generateExternalResource(Model model, Resource resource, T externalResource) {
        externalResource = baseEntityObjectMapper.generateBaseEntity(model, resource, externalResource);

        Statement statement = resource.getProperty(CVO.getProperty("name"));
        if(statement != null)
            externalResource.setName(statement.getObject().asLiteral().getString());

        statement = resource.getProperty(CVO.getProperty("url"));
        if(statement != null)
            externalResource.setUrl(statement.getObject().asLiteral().getString());

        return externalResource;
    }

    public <T extends ExternalResource> void createModel(T externalResource, Model model, Resource resource) {
        baseEntityObjectMapper.createMode(externalResource, model, resource);

        if (externalResource.getUrl() != null) {
            Literal literal = model.createTypedLiteral(externalResource.getUrl());
            model.add(resource, CVO.getProperty("url"), literal);
        }

        if (externalResource.getName() != null) {
            Literal literal = model.createTypedLiteral(externalResource.getName());
            model.add(resource, CVO.getProperty("name"), literal);
        }
    }
}
