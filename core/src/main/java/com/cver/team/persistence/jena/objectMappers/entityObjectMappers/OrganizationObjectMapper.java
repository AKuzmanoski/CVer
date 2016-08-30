package com.cver.team.persistence.jena.objectMappers.entityObjectMappers;

import com.cver.team.model.entity.Organization;
import com.cver.team.persistence.jena.namespaces.CVO;
import org.apache.jena.rdf.model.*;
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

        return generateOrganization(model, resource, organization);
    }

    public static <T extends Organization> T generateOrganization(Model model, Resource resource, T organization) {
        organization = AgentObjectMapper.generateAgent(model, resource, organization);

        // Owner
        StmtIterator owners = resource.listProperties(CVO.getProperty("owner"));
        while (owners.hasNext()) {
            Statement statement = owners.next();
            Resource owner = statement.getObject().asResource();
            organization.addOwner(PersonObjectMapper.generatePerson(model, owner));
        }

        return organization;
    }
}
