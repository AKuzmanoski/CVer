package com.cver.team.persistence.jena.objectMappers.entityObjectMappers;

import com.cver.team.model.entity.CV;
import com.cver.team.model.entity.Document;
import com.cver.team.model.entity.DocumentCard;
import com.cver.team.model.entity.Entity;
import com.cver.team.persistence.helper.URIMaker;
import com.cver.team.persistence.jena.namespaces.CVO;
import com.cver.team.persistence.jena.namespaces.CVR;
import com.cver.team.persistence.jena.objectMappers.dataObjectMappers.stringObjectMappers.ValuePropositionObjectMapper;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.impl.StatementImpl;

/**
 * Created by PC on 25/08/2016.
 */
public class DocumentObjectMapper {
    public static <T extends Document> T generateDocument(Model model, Resource resource, T document) {
        document = DocumentCardObjectMapper.generateDocument(model, resource, document);

        // Template
        Statement statement = resource.getProperty(CVO.getProperty("template"));
        if (statement != null)
            document.setTemplate(TemplateObjectMapper.generateTemplate(model, statement.getObject().asResource()));

        // Value Proposition
        statement = resource.getProperty(CVO.getProperty("valueProposition"));
        if (statement != null) {
            document.setValueProposition(ValuePropositionObjectMapper.generateValueProposition(model, statement.getObject().asResource()));
        }

        return document;
    }

    public static <T extends Document> Model createModel(T document, Model model, Resource resource) {
        DocumentCardObjectMapper.createModel(document, model, resource);

        if (document.getTemplate().getIdentifier() == null)
            TemplateObjectMapper.createModel(document.getTemplate(), model);
        model.add(new StatementImpl(resource, CVO.getProperty("template"), CVR.getResource(document.getTemplate().getIdentifier().getId())));

        if (document.getValueProposition() != null) {
            if (document.getValueProposition().getIdentifier() == null)
                ValuePropositionObjectMapper.createModel(document.getValueProposition(), model);
            model.add(new StatementImpl(resource, CVO.getProperty("valueProposition"), CVR.getResource(document.getValueProposition().getIdentifier().getId())));
        }

        return model;
    }
}
