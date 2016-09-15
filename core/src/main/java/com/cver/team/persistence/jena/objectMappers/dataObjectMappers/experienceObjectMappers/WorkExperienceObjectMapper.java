package com.cver.team.persistence.jena.objectMappers.dataObjectMappers.experienceObjectMappers;

import com.cver.team.model.data.ProjectExperience;
import com.cver.team.model.data.WorkExperience;
import com.cver.team.model.entity.Organization;
import com.cver.team.persistence.jena.helper.IdentifierGenerator;
import com.cver.team.persistence.jena.namespaces.CVO;
import com.cver.team.persistence.jena.namespaces.CVR;
import com.cver.team.persistence.jena.objectMappers.dataObjectMappers.ExperienceObjectMapper;
import com.cver.team.persistence.jena.objectMappers.entityObjectMappers.EducationalInstitutionObjectMapper;
import com.cver.team.persistence.jena.objectMappers.entityObjectMappers.OrganizationObjectMapper;
import com.cver.team.persistence.jena.objectMappers.externalResourceObjectMappers.PositionObjectMapper;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by PC on 25/08/2016.
 */
@Component
public class WorkExperienceObjectMapper {
    @Autowired
    ExperienceObjectMapper experienceObjectMapper;
    @Autowired
    OrganizationObjectMapper organizationObjectMapper;
    @Autowired
    PositionObjectMapper positionObjectMapper;
    @Autowired
    ProjectExperienceObjectMapper projectExperienceObjectMapper;

    public WorkExperience generateWorkExperience(Model model, Resource resource) {
        WorkExperience workExperience = (WorkExperience)experienceObjectMapper.generateExperience(model, resource);
        return workExperience;
    }

    public <T extends WorkExperience> T generateWorkExperience(Model model, Resource resource, T workExperience) {
        workExperience = experienceObjectMapper.generateExperience(model, resource, workExperience);

        // AcquiredFrom
        Statement statement = resource.getProperty(CVO.getProperty("acquiredFrom"));
        if(statement != null)
            workExperience.setAcquiredFrom(organizationObjectMapper.generateOrganization(model, statement.getObject().asResource()));

        // Position
        statement = resource.getProperty(CVO.getProperty("position"));
        if(statement != null)
            workExperience.setPosition(positionObjectMapper.generatePosition(model, statement.getObject().asResource()));

        return workExperience;
    }

    public WorkExperience generateExactlyWorkExperience(Model model, Resource resource) {
        return generateWorkExperience(model, resource, new WorkExperience());
    }

    public void createModel(WorkExperience experience, Model model) {
        if (experience.getIdentifier() != null)
            return;
        if (experience instanceof ProjectExperience) {
            projectExperienceObjectMapper.createModel((ProjectExperience) experience, model);
            return;
        }
        experience.setIdentifier(IdentifierGenerator.generateIdentifier());
        Resource resource = CVR.getResource(experience.getIdentifier().getId());
        createModel(experience, model, resource);
    }

    public <T extends WorkExperience> void createModel(T experience, Model model, Resource resource) {
        experienceObjectMapper.createModel(experience, model, resource);

        if (experience.getAcquiredFrom() != null && !(experience instanceof ProjectExperience)) {
            organizationObjectMapper.createModel((Organization) experience.getAcquiredFrom(), model);
            model.add(resource, CVO.getProperty("acquiredFrom"), CVR.getResource(experience.getAcquiredFrom().getIdentifier().getId()));
        }

        if (experience.getPosition() != null) {
            positionObjectMapper.createModel(experience.getPosition(), model);
            model.add(resource, CVO.getProperty("position"), CVR.getResource(experience.getPosition().getIdentifier().getId()));
        }
    }
}
