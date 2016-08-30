package com.cver.team.persistence.jena.objectMappers.entityObjectMappers;

import com.cver.team.model.entity.Project;
import com.cver.team.persistence.jena.namespaces.CVO;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.vocabulary.RDF;

/**
 * Created by PC on 17/08/2016.
 */
public class ProjectObjectMapper {
    public static Project generateProject(Model model, Resource resource) {
        Project project = new Project();

        project = EntityObjectMapper.generateEntity(model, resource, project);

        // External Link
        Statement statement = resource.getProperty(CVO.getProperty("url"));
        if (statement != null)
            project.setExternalLink(statement.getObject().asLiteral().getString());

        StmtIterator owners = resource.listProperties(CVO.getProperty("owner"));
        while (owners.hasNext()) {
            statement = owners.next();
            Resource owner = statement.getObject().asResource();
            if (owner.hasProperty(RDF.type, CVO.getResource("Person")))
                project.addOwner(PersonObjectMapper.generatePerson(model, owner));
            else project.addOwner(OrganizationObjectMapper.generateOrganization(model, owner));
        }

        return project;
    }
}
