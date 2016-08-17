package com.cver.team.persistence.jena.objectMappers;

import com.cver.team.model.entity.Entity;
import com.cver.team.model.literal.Identifier;
import com.cver.team.persistence.jena.helper.DateTimeConverter;
import com.cver.team.persistence.jena.namespaces.CVO;
import com.cver.team.persistence.jena.namespaces.CVR;
import com.cver.team.persistence.jena.namespaces.LUC;
import org.apache.jena.base.Sys;
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
        return null;
    }

    public static <T extends Entity> T generateEntity(Model model, Resource resource, T entity) {

        entity = BaseEntityObjectMapper.generateBaseEntity(model, resource, entity);

        // Name
        Statement statement = resource.getProperty(CVO.getProperty("name"));
        if (statement != null)
            entity.setName(statement.getObject().asLiteral().getString());

        // description
        statement = resource.getProperty(CVO.getProperty("description"));
        if (statement != null)
            entity.setDescription(statement.getObject().asLiteral().getString());

        // Profile Picture
        statement = resource.getProperty(CVO.getProperty("cover"));
        if (statement != null)
            entity.setCoverPictureUrl(statement.getObject().asLiteral().getString());

        // Public
        statement = resource.getProperty(CVO.getProperty("public"));
        if (statement != null)
            entity.setPublic(statement.getObject().asLiteral().getBoolean());

        statement = resource.getProperty(CVO.getProperty("creationDate"));
        if (statement != null)
            entity.setCreationDate(DateTimeConverter.getDate(statement.getObject()));

        statement = resource.getProperty(CVO.getProperty("lastModified"));
        if (statement != null)
            entity.setLastModified(DateTimeConverter.getDate(statement.getObject()));

        return entity;
    }
}
