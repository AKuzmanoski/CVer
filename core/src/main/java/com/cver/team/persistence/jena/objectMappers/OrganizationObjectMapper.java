package com.cver.team.persistence.jena.objectMappers;

import com.cver.team.model.entity.Organization;
import com.cver.team.persistence.jena.helper.DateTimeConverter;
import com.cver.team.persistence.jena.namespaces.CVO;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ResIterator;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.vocabulary.RDF;

/**
 * Created by PC on 17/08/2016.
 */
public class OrganizationObjectMapper {
    public static Organization generatOrganization(Model model) {
        ResIterator iterator = model.listResourcesWithProperty(RDF.type, CVO.getResource("Organization"));
        if (iterator.hasNext()) {
            Resource resource = iterator.nextResource();
            return generateOrganization(model, resource);
        } else
            return null;
    }

    public static Organization generateOrganization(Model model, Resource resource) {
        Organization organization = new Organization();

        organization = AgentObjectMapper.generateAgent(model, resource, organization);

        Statement statement = resource.getProperty(CVO.getProperty("dateOfFoundation"));
        if (statement != null)
            organization.setDateOfFoundation(DateTimeConverter.getDate(statement.getObject()));

        // Owner
        statement = resource.getProperty(CVO.getProperty("owner"));
        if (statement != null)
            organization.setOwner(PersonObjectMapper.generatePerson(model, statement.getObject().asResource()));

        return organization;
    }
}
