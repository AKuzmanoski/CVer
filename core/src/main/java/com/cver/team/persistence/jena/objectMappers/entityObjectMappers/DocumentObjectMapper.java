package com.cver.team.persistence.jena.objectMappers.entityObjectMappers;

import com.cver.team.model.entity.Document;
import com.cver.team.persistence.jena.namespaces.CVO;
import com.cver.team.persistence.jena.objectMappers.dataObjectMappers.stringObjectMappers.ValuePropositionObjectMapper;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;

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
}
