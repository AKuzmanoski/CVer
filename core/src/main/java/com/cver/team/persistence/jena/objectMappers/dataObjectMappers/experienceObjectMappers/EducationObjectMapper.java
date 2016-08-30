package com.cver.team.persistence.jena.objectMappers.dataObjectMappers.experienceObjectMappers;

import com.cver.team.model.data.EducationalExperience;
import com.cver.team.persistence.jena.namespaces.CVO;
import com.cver.team.persistence.jena.objectMappers.dataObjectMappers.ExperienceObjectMapper;
import com.cver.team.persistence.jena.objectMappers.entityObjectMappers.EducationalInstitutionObjectMapper;
import com.cver.team.persistence.jena.objectMappers.externalResourceObjectMappers.DegreeObjectMapper;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;

/**
 * Created by PC on 25/08/2016.
 */
public class EducationObjectMapper {
    public static EducationalExperience generateEducationalExperience(Model model, Resource resource) {
        EducationalExperience educationalExperience = new EducationalExperience();
        educationalExperience = ExperienceObjectMapper.generateExperience(model, resource, educationalExperience);

        // AcquiredFrom
        Statement statement = resource.getProperty(CVO.getProperty("acquiredFrom"));
        if(statement != null)
            educationalExperience.setAcquiredFrom(EducationalInstitutionObjectMapper.generateEducationalInstitution(model, statement.getObject().asResource()));

        // Gdp
        statement = resource.getProperty(CVO.getProperty("gdp"));
        if(statement != null)
            educationalExperience.setGdp(statement.getObject().asLiteral().getString());

        // Major
        statement = resource.getProperty(CVO.getProperty("major"));
        if(statement != null)
            educationalExperience.setMajor(statement.getObject().asLiteral().getString());

        // Degree
        statement = resource.getProperty(CVO.getProperty("degree"));
        if(statement != null)
            educationalExperience.setDegree(DegreeObjectMapper.generateDegree(model, statement.getObject().asResource()));

        return educationalExperience;
    }
}
