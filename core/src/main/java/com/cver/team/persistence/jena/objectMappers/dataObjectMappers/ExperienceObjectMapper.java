package com.cver.team.persistence.jena.objectMappers.dataObjectMappers;

import com.cver.team.model.data.EducationalExperience;
import com.cver.team.model.data.Experience;
import com.cver.team.model.data.ProjectExperience;
import com.cver.team.model.data.WorkExperience;
import com.cver.team.persistence.jena.helper.IdentifierGenerator;
import com.cver.team.persistence.jena.namespaces.CVO;
import com.cver.team.persistence.jena.namespaces.CVR;
import com.cver.team.persistence.jena.objectMappers.dataObjectMappers.DataObjectMapper;
import com.cver.team.persistence.jena.objectMappers.dataObjectMappers.experienceObjectMappers.EducationObjectMapper;
import com.cver.team.persistence.jena.objectMappers.dataObjectMappers.experienceObjectMappers.ProjectExperienceObjectMapper;
import com.cver.team.persistence.jena.objectMappers.dataObjectMappers.experienceObjectMappers.WorkExperienceObjectMapper;
import com.cver.team.persistence.jena.objectMappers.dataObjectMappers.locatonObjectMappers.AddressObjectMapper;
import com.cver.team.persistence.jena.objectMappers.dataObjectMappers.locatonObjectMappers.CityObjectMapper;
import com.cver.team.persistence.jena.objectMappers.dataObjectMappers.locatonObjectMappers.CountryObjectMapper;
import com.cver.team.persistence.jena.objectMappers.externalResourceObjectMappers.PeriodObjectMapper;
import org.apache.jena.assembler.Mode;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.vocabulary.RDF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by PC on 25/08/2016.
 */
@Component
public class ExperienceObjectMapper {
    @Autowired
    DataObjectMapper dataObjectMapper;
    @Autowired
    AddressObjectMapper addressObjectMapper;
    @Autowired
    CityObjectMapper cityObjectMapper;
    @Autowired
    CountryObjectMapper countryObjectMapper;
    @Autowired
    EducationObjectMapper educationObjectMapper;
    @Autowired
    WorkExperienceObjectMapper workExperienceObjectMapper;
    @Autowired
    ProjectExperienceObjectMapper projectExperienceObjectMapper;
    @Autowired
    PeriodObjectMapper periodObjectMapper;
    @Autowired
    LocationObjectMapper locationObjectMapper;

    public <T extends Experience> T generateExperience(Model model, Resource resource, T experience) {
        experience = dataObjectMapper.generateData(model, resource, experience);

        // Address
        Statement statement = resource.getProperty(CVO.getProperty("address"));
        if (statement != null) {
            experience.setLocation(addressObjectMapper.generateAddress(model, statement.getObject().asResource()));
        }

        // City
        statement = resource.getProperty(CVO.getProperty("city"));
        if (statement != null) {
            experience.setLocation(cityObjectMapper.generateCity(model, statement.getObject().asResource()));
        }

        // Country
        statement = resource.getProperty(CVO.getProperty("country"));
        if (statement != null) {
            experience.setLocation(countryObjectMapper.generateCountry(model, statement.getObject().asResource()));
        }

        // Period
        statement = resource.getProperty(CVO.getProperty("period"));
        if (statement != null) {
            experience.setPeriod(periodObjectMapper.generatePeriod(model, statement.getObject().asResource()));
        }

        return experience;
    }

    public Experience generateExperience(Model model, Resource resource) {
        if (resource.hasProperty(RDF.type, CVO.getResource("EducationalExperience")))
            return educationObjectMapper.generateEducationalExperience(model, resource);
        else if (resource.hasProperty(RDF.type, CVO.getResource("ProjectExperience")))
            return projectExperienceObjectMapper.generateProjectExperience(model, resource);
        else if (resource.hasProperty(RDF.type, CVO.getResource("WorkExperience")))
            return workExperienceObjectMapper.generateExactlyWorkExperience(model, resource);
        else return generateExperience(model, resource, new Experience());
    }

    public void createModel(Experience experience, Model model) {
        if (experience.getIdentifier() != null)
            return;
        if (experience instanceof EducationalExperience) {
            educationObjectMapper.createModel((EducationalExperience) experience, model);
            return;
        }
        if (experience instanceof ProjectExperience) {
            projectExperienceObjectMapper.createModel((ProjectExperience) experience, model);
            return;
        }
        if (experience instanceof WorkExperience) {
            workExperienceObjectMapper.createModel((WorkExperience) experience, model);
            return;
        }
        experience.setIdentifier(IdentifierGenerator.generateIdentifier());
        Resource resource = CVR.getResource(experience.getIdentifier().getId());
        createModel(experience, model, resource);
    }

    public <T extends Experience> void createModel(T experience, Model model, Resource resource) {
        dataObjectMapper.createModel(experience, model, resource);

        if (experience.getPeriod() != null) {
            periodObjectMapper.createModel(experience.getPeriod(), model);
            model.add(resource, CVO.getProperty("period"), CVR.getResource(experience.getPeriod().getIdentifier().getId()));
        }
        if (experience.getLocation() != null) {
            locationObjectMapper.createModel(experience.getLocation(), model);
            model.add(resource, CVO.getProperty("location"), CVR.getResource(experience.getLocation().getIdentifier().getId()));
        }
    }
}
