package com.cver.team.persistence.jena.objectMappers.entityObjectMappers;

import com.cver.team.model.entity.Document;
import com.cver.team.model.entity.DocumentCard;
import com.cver.team.model.entity.Person;
import com.cver.team.model.externalresource.Period;
import com.cver.team.persistence.jena.namespaces.CVO;
import com.cver.team.persistence.jena.namespaces.CVR;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.impl.StatementImpl;
import org.apache.jena.vocabulary.RDF;

/**
 * Created by PC on 17/08/2016.
 */
public class DocumentCardObjectMapper {
    public static <T extends DocumentCard> T generateDocument(Model model, Resource resource, T document) {
        document = EntityObjectMapper.generateEntity(model, resource, document);

        // Title
        Statement statement = resource.getProperty(CVO.getProperty("title"));
        if (statement != null)
            document.setTitle(statement.getObject().asLiteral().getString());

        statement = resource.getProperty(CVO.getProperty("owner"));
        if (statement != null) {
            Resource owner = statement.getObject().asResource();
            if (owner.hasProperty(RDF.type, CVO.getResource("Person"))) {
                Person person = PersonObjectMapper.generatePerson(model, owner);
                document.setOwner(person);
            }
            else document.setOwner(OrganizationObjectMapper.generateOrganization(model, owner));
        }

        return document;
    }

    public static <T extends DocumentCard> void createModel(T document, Model model, Resource resource) {
        EntityObjectMapper.createModel(document, model, resource);

        if (document.getTitle() != null)
            model.add(new StatementImpl(resource, CVO.getProperty("title"), model.createTypedLiteral(document.getTitle())));

        if (document.getOwner().getIdentifier() == null)
            AgentObjectMapper.createModel(document.getOwner(), model);
        model.add(new StatementImpl(resource, CVO.getProperty("owner"), CVR.getResource(document.getOwner().getIdentifier().getId())));
    }
}
