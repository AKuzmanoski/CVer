package com.cver.team.persistence.jena.objectMappers.dataObjectMappers.experienceObjectMappers;

import com.cver.team.model.data.ProjectExperience;
import com.cver.team.persistence.jena.namespaces.CVO;
import com.cver.team.persistence.jena.objectMappers.entityObjectMappers.EducationalInstitutionObjectMapper;
import com.cver.team.persistence.jena.objectMappers.entityObjectMappers.ProjectObjectMapper;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;

/**
 * Created by PC on 25/08/2016.
 */
public class ProjectExperienceObjectMapper {
    public static ProjectExperience generateProjectExperience(Model model, Resource resource) {
        ProjectExperience projectExperience = new ProjectExperience();
        projectExperience = WorkExperienceObjectMapper.generateWorkExperience(model, resource, projectExperience);

        // AcquiredFrom
        Statement statement = resource.getProperty(CVO.getProperty("acquiredFrom"));
        if(statement != null)
            projectExperience.setAcquiredFrom(ProjectObjectMapper.generateProject(model, statement.getObject().asResource()));

        return projectExperience;
    }
}
