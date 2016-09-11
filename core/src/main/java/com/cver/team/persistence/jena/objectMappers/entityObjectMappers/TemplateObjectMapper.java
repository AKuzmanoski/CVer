package com.cver.team.persistence.jena.objectMappers.entityObjectMappers;

import com.cver.team.model.entity.Agent;
import com.cver.team.model.entity.Template;
import com.cver.team.persistence.jena.helper.IdentifierGenerator;
import com.cver.team.persistence.jena.namespaces.CVO;
import com.cver.team.persistence.jena.namespaces.CVR;
import com.cver.team.persistence.jena.namespaces.LUC;
import org.apache.jena.rdf.model.*;
import org.apache.jena.rdf.model.impl.StatementImpl;
import org.apache.jena.vocabulary.RDF;

import java.util.*;

/**
 * Created by PC on 17/08/2016.
 */
public class TemplateObjectMapper {

    public static Template generateTemplate(Model model, Resource resource) {
        Template template = new Template();

        template = EntityObjectMapper.generateEntity(model, resource, template);

        // External Link
        Statement statement = resource.getProperty(CVO.getProperty("url"));
        if (statement != null)
            template.setUrl(statement.getObject().asLiteral().getString());

        // Val
        statement = resource.getProperty(CVO.getProperty("val"));
        if (statement != null)
            template.setValue(statement.getObject().asLiteral().getString());

        StmtIterator owners = resource.listProperties(CVO.getProperty("owner"));
        while (owners.hasNext()) {
            statement = owners.next();
            Resource owner = statement.getObject().asResource();
            if (owner.hasProperty(RDF.type, CVO.getResource("Person")))
                template.addOwner(PersonObjectMapper.generatePerson(model, owner));
            else template.addOwner(OrganizationObjectMapper.generateOrganization(model, owner));
        }

        return template;
    }

    public static List<Template> generateTemplates(Model model) {
        Map<Double, Template> map = new TreeMap<>(new Comparator<Double>() {
            @Override
            public int compare(Double o1, Double o2) {
                return o2.compareTo(o1);
            }
        });
        ResIterator iterator = model.listResourcesWithProperty(LUC.getProperty("score"));
        while (iterator.hasNext()) {
            Resource resource = iterator.next();
            Double score = resource.getProperty(LUC.getProperty("score")).getObject().asLiteral().getDouble();
            map.put(score, generateTemplate(model, resource));
        }
        return new ArrayList<>(map.values());
    }

    public static Template generateTemplate(Model model, String id) {
        Resource resource = model.getResource(id);
        return generateTemplate(model, resource);
    }

    public static void createModel(Template template, Model model) {
        if (template.getIdentifier() != null)
            return;
        template.setIdentifier(IdentifierGenerator.generateIdentifier());
        Resource templateResource = model.createResource(template.getIdentifier().getURI());

        createModel(template, model, templateResource);
    }

    public static <T extends Template> void createModel(T template, Model model, Resource resource) {
        EntityObjectMapper.createModel(template, model, resource);
        model.add(new StatementImpl(resource, RDF.type, CVO.getResource("Template")));

        if (template.getValue() != null)
            model.add(new StatementImpl(resource, CVO.getProperty("val"), model.createTypedLiteral(template.getValue())));

        if (template.getUrl() != null)
            model.add(new StatementImpl(resource, CVO.getProperty("url"), model.createTypedLiteral(template.getUrl())));

        for (Agent owner : template.getOwners()) {
            if (owner.getIdentifier() == null)
                AgentObjectMapper.createModel(owner, model);
            model.add(new StatementImpl(resource, CVO.getProperty("owner"), CVR.getResource(owner.getIdentifier().getId())));
        }
    }
}
