package com.cver.team.persistence.jena.objectMappers.entityObjectMappers;

import com.cver.team.model.entity.DocumentCard;
import com.cver.team.model.entity.Entity;
import com.cver.team.persistence.helper.URIMaker;
import com.cver.team.persistence.jena.helper.DateTimeConverter;
import com.cver.team.persistence.jena.namespaces.CVO;
import com.cver.team.persistence.jena.namespaces.CVR;
import com.cver.team.persistence.jena.namespaces.LUC;
import com.cver.team.persistence.jena.objectMappers.BaseEntityObjectMapper;
import org.apache.jena.rdf.model.*;
import org.apache.jena.rdf.model.impl.StatementImpl;
import org.apache.jena.vocabulary.RDF;

import java.util.*;

/**
 * Created by PC on 17/08/2016.
 */
public class EntityObjectMapper {
    public static List<Entity> generateEntities(Model model) {
        Map<Double, Entity> map = new TreeMap<>(new Comparator<Double>() {
            @Override
            public int compare(Double o1, Double o2) {
                return o2.compareTo(o1);
            }
        });
        ResIterator iterator = model.listResourcesWithProperty(LUC.getProperty("score"));
        while (iterator.hasNext()) {
            Resource resource = iterator.next();
            Double score = resource.getProperty(LUC.getProperty("score")).getObject().asLiteral().getDouble();
            map.put(score, generateEntity(model, resource));
        }
        return new ArrayList<>(map.values());
    }

    public static Entity generateEntity(Model model, Resource resource) {
        if (resource.hasProperty(RDF.type, CVO.getResource("Person"))) {
            return PersonObjectMapper.generatePerson(model, resource);
        }
        if (resource.hasProperty(RDF.type, CVO.getResource("Organization"))) {
            return OrganizationObjectMapper.generateOrganization(model, resource);
        }
        if (resource.hasProperty(RDF.type, CVO.getResource("CV"))) {
            return CvCardObjectMapper.generateCvCard(model, resource);
        }
        if (resource.hasProperty(RDF.type, CVO.getResource("Certificate"))) {
            return CertificateCardObjectMapper.generateCertificate(model, resource);
        }
        if (resource.hasProperty(RDF.type, CVO.getResource("Call"))) {
            return CallObjectMapper.generateCall(model, resource);
        }
        if (resource.hasProperty(RDF.type, CVO.getResource("Template"))) {
            return TemplateObjectMapper.generateTemplate(model, resource);
        }
        if (resource.hasProperty(RDF.type, CVO.getResource("Project"))) {
            return ProjectObjectMapper.generateProject(model, resource);
        }
        return null;
    }

    public static <T extends Entity> T generateEntity(Model model, Resource resource, T entity) {

        entity = BaseEntityObjectMapper.generateBaseEntity(model, resource, entity);

        // Name
        Statement statement = resource.getProperty(CVO.getProperty("name"));
        if (statement != null)
            entity.setName(statement.getObject().asLiteral().getString());

        // Cover Picture
        statement = resource.getProperty(CVO.getProperty("cover"));
        if (statement != null) {
            Resource cover = statement.getObject().asResource();
            statement = cover.getProperty(CVO.getProperty("url"));
            if (statement != null)
                entity.setCoverPictureUrl(statement.getObject().asLiteral().getString());
        }

        statement = resource.getProperty(CVO.getProperty("creationDate"));
        if (statement != null)
            entity.setCreationDate(DateTimeConverter.getDate(statement.getObject()));

        statement = resource.getProperty(CVO.getProperty("lastModified"));
        if (statement != null)
            entity.setLastModified(DateTimeConverter.getDate(statement.getObject()));

        return entity;
    }

    public static <T extends Entity> void createModel(T entity, Model model, Resource resource) {

        BaseEntityObjectMapper.createMode(entity, model, resource);

        // Create current date
        java.util.Date date = new java.util.Date();
        Calendar calendar = DateTimeConverter.getCalendar(date);
        Literal dateTime = model.createTypedLiteral(calendar);

        // Set creation and last modified dates
        model.add(new StatementImpl(resource, CVO.getProperty("creationDate"), dateTime));
        model.add(new StatementImpl(resource, CVO.getProperty("lastModified"), dateTime));

        // Set cover picture
        String coverId = URIMaker.generateUri();
        Resource cover = CVR.getResource(coverId);
        Literal coverUrl = model.createTypedLiteral(entity.getCoverPictureUrl());
        model.add(new StatementImpl(resource, CVO.getProperty("cover"), cover));
        model.add(new StatementImpl(cover, CVO.getProperty("url"), coverUrl));
        model.add(new StatementImpl(cover, CVO.getProperty("creationDate"), dateTime));
        model.add(new StatementImpl(cover, CVO.getProperty("lastModified"), dateTime));

        // Set name
        if (entity.getName() != null)
            model.add(new StatementImpl(resource, CVO.getProperty("name"), model.createTypedLiteral(entity.getName())));
    }
}
