package com.cver.team.persistence.jena.objectMappers.dataObjectMappers.experienceObjectMappers;

import com.cver.team.model.data.ProjectExperience;
import com.cver.team.model.entity.Organization;
import com.cver.team.model.entity.Project;
import com.cver.team.persistence.jena.helper.IdentifierGenerator;
import com.cver.team.persistence.jena.namespaces.CVO;
import com.cver.team.persistence.jena.namespaces.CVR;
import com.cver.team.persistence.jena.objectMappers.dataObjectMappers.ExperienceObjectMapper;
import com.cver.team.persistence.jena.objectMappers.entityObjectMappers.EducationalInstitutionObjectMapper;
import com.cver.team.persistence.jena.objectMappers.entityObjectMappers.ProjectObjectMapper;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by PC on 25/08/2016.
 */
@Component
public class ProjectExperienceObjectMapper {
    @Autowired
    WorkExperienceObjectMapper workExperienceObjectMapper;
    @Autowired
    ProjectObjectMapper projectObjectMapper;

    public ProjectExperience generateProjectExperience(Model model, Resource resource) {
        ProjectExperience projectExperience = new ProjectExperience();
        projectExperience = workExperienceObjectMapper.generateWorkExperience(model, resource, projectExperience);

        // AcquiredFrom
        Statement statement = resource.getProperty(CVO.getProperty("acquiredFrom"));
        if(statement != null)
            projectExperience.setAcquiredFrom(projectObjectMapper.generateProject(model, statement.getObject().asResource()));

        return projectExperience;
    }

    public void createModel(ProjectExperience experience, Model model) {
        if (experience.getIdentifier() != null)
            return;
        experience.setIdentifier(IdentifierGenerator.generateIdentifier());
        Resource resource = CVR.getResource(experience.getIdentifier().getId());
        createModel(experience, model, resource);
    }

    public <T extends ProjectExperience> void createModel(T experience, Model model, Resource resource) {
        if (experience.getAcquiredFrom() != null && !(experience instanceof ProjectExperience)) {
            projectObjectMapper.createModel((Project) experience.getAcquiredFrom(), model);
            model.add(resource, CVO.getProperty("acquiredFrom"), CVR.getResource(experience.getAcquiredFrom().getIdentifier().getId()));
        }
    }
}
