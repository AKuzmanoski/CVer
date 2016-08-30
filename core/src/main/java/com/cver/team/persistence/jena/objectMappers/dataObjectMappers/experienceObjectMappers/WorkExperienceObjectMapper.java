package com.cver.team.persistence.jena.objectMappers.dataObjectMappers.experienceObjectMappers;

import com.cver.team.model.data.ProjectExperience;
import com.cver.team.model.data.WorkExperience;
import com.cver.team.persistence.jena.namespaces.CVO;
import com.cver.team.persistence.jena.objectMappers.dataObjectMappers.ExperienceObjectMapper;
import com.cver.team.persistence.jena.objectMappers.entityObjectMappers.EducationalInstitutionObjectMapper;
import com.cver.team.persistence.jena.objectMappers.entityObjectMappers.OrganizationObjectMapper;
import com.cver.team.persistence.jena.objectMappers.externalResourceObjectMappers.PositionObjectMapper;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;

/**
 * Created by PC on 25/08/2016.
 */
public class WorkExperienceObjectMapper {
    public static WorkExperience generateWorkExperience(Model model, Resource resource) {
        WorkExperience workExperience = (WorkExperience)ExperienceObjectMapper.generateExperience(model, resource);
        return workExperience;
    }

    public static <T extends WorkExperience> T generateWorkExperience(Model model, Resource resource, T workExperience) {
        workExperience = ExperienceObjectMapper.generateExperience(model, resource, workExperience);

        // AcquiredFrom
        Statement statement = resource.getProperty(CVO.getProperty("acquiredFrom"));
        if(statement != null)
            workExperience.setAcquiredFrom(OrganizationObjectMapper.generateOrganization(model, statement.getObject().asResource()));

        // Position
        statement = resource.getProperty(CVO.getProperty("position"));
        if(statement != null)
            workExperience.setPosition(PositionObjectMapper.generatePosition(model, statement.getObject().asResource()));

        return workExperience;
    }

    public static WorkExperience generateExactlyWorkExperience(Model model, Resource resource) {
        return generateWorkExperience(model, resource, new WorkExperience());
    }
}
