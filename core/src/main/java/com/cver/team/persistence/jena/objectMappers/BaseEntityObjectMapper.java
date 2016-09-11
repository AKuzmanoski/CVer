package com.cver.team.persistence.jena.objectMappers;

import com.cver.team.model.BaseEntity;
import com.cver.team.model.entity.DocumentCard;
import com.cver.team.model.literal.Identifier;
import com.cver.team.persistence.jena.namespaces.CVO;
import com.cver.team.persistence.jena.namespaces.CVR;
import com.cver.team.persistence.jena.objectMappers.externalResourceObjectMappers.TagObjectMapper;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.rdf.model.impl.StatementImpl;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.RDFS;

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

        // description
        Statement statement = resource.getProperty(CVO.getProperty("description"));
        if (statement != null)
            baseEntity.setDescription(statement.getObject().asLiteral().getString());

        // Public
        statement = resource.getProperty(CVO.getProperty("public"));
        if (statement != null)
            baseEntity.setPublic(statement.getObject().asLiteral().getBoolean());


        // Tags
        StmtIterator tags = resource.listProperties(CVO.getProperty("tag"));
        while(tags.hasNext()) {
            Resource tag = tags.next().getObject().asResource();
            baseEntity.addTag(TagObjectMapper.generateTag(model, tag));
        }

        // Types
        StmtIterator types = resource.listProperties(RDF.type);
        while (types.hasNext()) {
            Resource type = types.next().getObject().asResource();
            baseEntity.addType(TypeObjectMapper.generateType(model, type));
        }

        return baseEntity;
    }

    public static <T extends BaseEntity> void createMode(T baseEntity, Model model, Resource resource) {
        if (baseEntity.getDescription() != null)
            model.add(new StatementImpl(resource, CVO.getProperty("description"), model.createTypedLiteral(baseEntity.getDescription())));
        model.add(new StatementImpl(resource, CVO.getProperty("public"), model.createTypedLiteral(baseEntity.isPublic())));
    }
}
