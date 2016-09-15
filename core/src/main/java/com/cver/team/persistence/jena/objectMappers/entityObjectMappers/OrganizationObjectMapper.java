package com.cver.team.persistence.jena.objectMappers.entityObjectMappers;

import com.cver.team.model.entity.Agent;
import com.cver.team.model.entity.Organization;
import com.cver.team.model.entity.Person;
import com.cver.team.persistence.jena.helper.IdentifierGenerator;
import com.cver.team.persistence.jena.namespaces.CVO;
import com.cver.team.persistence.jena.namespaces.CVR;
import org.apache.jena.rdf.model.*;
import org.apache.jena.rdf.model.impl.StatementImpl;
import org.apache.jena.vocabulary.RDF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by PC on 17/08/2016.
 */
@Component
public class OrganizationObjectMapper {
    @Autowired
    PersonObjectMapper personObjectMapper;
    @Autowired
    AgentObjectMapper agentObjectMapper;

    public Organization generatOrganization(Model model) {
        ResIterator iterator = model.listResourcesWithProperty(RDF.type, CVO.getResource("Organization"));
        if (iterator.hasNext()) {
            Resource resource = iterator.nextResource();
            return generateOrganization(model, resource);
        } else
            return null;
    }

    public Organization generateOrganization(Model model, Resource resource) {
        Organization organization = new Organization();

        return generateOrganization(model, resource, organization);
    }

    public <T extends Organization> T generateOrganization(Model model, Resource resource, T organization) {
        organization = agentObjectMapper.generateAgent(model, resource, organization);

        // Owner
        StmtIterator owners = resource.listProperties(CVO.getProperty("owner"));
        while (owners.hasNext()) {
            Statement statement = owners.next();
            Resource owner = statement.getObject().asResource();
            organization.addOwner(personObjectMapper.generatePerson(model, owner));
        }

        return organization;
    }

    public Organization generateOrganization(Model model, String id) {
        Resource organization = model.getResource(id);
        return generateOrganization(model, organization);
    }

    public void createModel(Organization organization, Model model) {
        if(organization.getIdentifier() != null)
            return;

        organization.setIdentifier(IdentifierGenerator.generateIdentifier());
        Resource resource = CVR.getResource(organization.getIdentifier().getId());
        createModel(organization, model, resource);
    }

    public <T extends Organization> void createModel(T organization, Model model, Resource resource) {
        agentObjectMapper.createModel(organization, model, resource);
        for (Person owner : organization.getOwners()) {
            if (owner.getIdentifier() == null)
                personObjectMapper.createModel(owner, model);
            model.add(new StatementImpl(resource, CVO.getProperty("owner"), CVR.getResource(owner.getIdentifier().getId())));
        }
    }
}
