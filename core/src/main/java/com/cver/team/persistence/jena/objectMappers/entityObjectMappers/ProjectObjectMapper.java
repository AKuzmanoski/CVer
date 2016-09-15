package com.cver.team.persistence.jena.objectMappers.entityObjectMappers;

import com.cver.team.model.entity.Project;
import com.cver.team.persistence.jena.helper.IdentifierGenerator;
import com.cver.team.persistence.jena.namespaces.CVO;
import com.cver.team.persistence.jena.namespaces.CVR;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.vocabulary.RDF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by PC on 17/08/2016.
 */
@Component
public class ProjectObjectMapper {
    @Autowired
    EntityObjectMapper entityObjectMapper;
    @Autowired
    PersonObjectMapper personObjectMapper;
    @Autowired
    OrganizationObjectMapper organizationObjectMapper;

    public Project generateProject(Model model, Resource resource) {
        Project project = new Project();

        project = entityObjectMapper.generateEntity(model, resource, project);

        // External Link
        Statement statement = resource.getProperty(CVO.getProperty("url"));
        if (statement != null)
            project.setExternalLink(statement.getObject().asLiteral().getString());

        StmtIterator owners = resource.listProperties(CVO.getProperty("owner"));
        while (owners.hasNext()) {
            statement = owners.next();
            Resource owner = statement.getObject().asResource();
            if (owner.hasProperty(RDF.type, CVO.getResource("Person")))
                project.addOwner(personObjectMapper.generatePerson(model, owner));
            else project.addOwner(organizationObjectMapper.generateOrganization(model, owner));
        }

        return project;
    }

    public void createModel(Project project, Model model) {
        if (project.getIdentifier() != null)
            return;
        project.setIdentifier(IdentifierGenerator.generateIdentifier());
        Resource resource = CVR.getResource(project.getIdentifier().getId());
        createModel(project, model, resource);
    }

    public <T extends Project> void createModel(T project, Model model, Resource resource) {
        entityObjectMapper.createModel(project, model, resource);
    }
}
