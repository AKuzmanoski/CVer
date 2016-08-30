package com.cver.team.persistence.jena.objectMappers.entityObjectMappers;

import com.cver.team.model.entity.CVTemplate;
import com.cver.team.model.entity.CertificateTemplate;
import com.cver.team.model.entity.Entity;
import com.cver.team.model.entity.Template;
import com.cver.team.persistence.jena.namespaces.CVO;
import com.cver.team.persistence.jena.namespaces.CVR;
import com.cver.team.persistence.jena.namespaces.LUC;
import org.apache.jena.rdf.model.*;
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
        while(iterator.hasNext()) {
            Resource resource = iterator.next();
            Double score = resource.getProperty(LUC.getProperty("score")).getObject().asLiteral().getDouble();
            map.put(score, generateTemplate(model, resource));
        }
        return new ArrayList<>(map.values());
    }
}
