package com.cver.team.persistence.jena.objectMappers.dataObjectMappers.experienceObjectMappers;

import com.cver.team.model.data.EducationalExperience;
import com.cver.team.persistence.jena.helper.IdentifierGenerator;
import com.cver.team.persistence.jena.namespaces.CVO;
import com.cver.team.persistence.jena.namespaces.CVR;
import com.cver.team.persistence.jena.objectMappers.dataObjectMappers.ExperienceObjectMapper;
import com.cver.team.persistence.jena.objectMappers.entityObjectMappers.EducationalInstitutionObjectMapper;
import com.cver.team.persistence.jena.objectMappers.externalResourceObjectMappers.DegreeObjectMapper;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by PC on 25/08/2016.
 */
@Component
public class EducationObjectMapper {
    @Autowired
    ExperienceObjectMapper experienceObjectMapper;
    @Autowired
    EducationalInstitutionObjectMapper educationalInstitutionObjectMapper;
    @Autowired
    DegreeObjectMapper degreeObjectMapper;

    public EducationalExperience generateEducationalExperience(Model model, Resource resource) {
        EducationalExperience educationalExperience = new EducationalExperience();
        educationalExperience = experienceObjectMapper.generateExperience(model, resource, educationalExperience);

        // AcquiredFrom
        Statement statement = resource.getProperty(CVO.getProperty("acquiredFrom"));
        if(statement != null)
            educationalExperience.setAcquiredFrom(educationalInstitutionObjectMapper.generateEducationalInstitution(model, statement.getObject().asResource()));

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
            educationalExperience.setDegree(degreeObjectMapper.generateDegree(model, statement.getObject().asResource()));

        return educationalExperience;
    }

    public void createModel(EducationalExperience experience, Model model) {
        if (experience.getIdentifier() != null)
            return;
        experience.setIdentifier(IdentifierGenerator.generateIdentifier());
        Resource resource = CVR.getResource(experience.getIdentifier().getId());
        createModel(experience, model, resource);
    }

    public <T extends EducationalExperience> void createModel(T experience, Model model, Resource resource) {
        experienceObjectMapper.createModel(experience, model, resource);

        if (experience.getDegree() != null) {
            degreeObjectMapper.createModel(experience.getDegree(), model);
            model.add(resource, CVO.getProperty("degree"), CVR.getResource(experience.getDegree().getIdentifier().getId()));
        }

        if (experience.getGdp() != null) {
            Literal literal = model.createTypedLiteral(experience.getGdp());
            model.add(resource, CVO.getProperty("gdp"), literal);
        }

        if (experience.getMajor() != null) {
            Literal literal = model.createTypedLiteral(experience.getMajor());
            model.add(resource, CVO.getProperty("major"), literal);
        }
    }
}
