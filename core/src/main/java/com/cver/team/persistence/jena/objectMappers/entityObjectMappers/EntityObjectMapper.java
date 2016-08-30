package com.cver.team.persistence.jena.objectMappers.entityObjectMappers;

import com.cver.team.model.entity.Entity;
import com.cver.team.persistence.jena.helper.DateTimeConverter;
import com.cver.team.persistence.jena.namespaces.CVO;
import com.cver.team.persistence.jena.namespaces.LUC;
import com.cver.team.persistence.jena.objectMappers.BaseEntityObjectMapper;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ResIterator;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
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
        while(iterator.hasNext()) {
            Resource resource = iterator.next();
            System.out.println(resource.getURI());
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

        // Profile Picture
        statement = resource.getProperty(CVO.getProperty("cover"));
        if (statement != null) {
            Resource cover =  statement.getObject().asResource();
            statement = cover.getProperty(CVO.getProperty("url"));
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
}
