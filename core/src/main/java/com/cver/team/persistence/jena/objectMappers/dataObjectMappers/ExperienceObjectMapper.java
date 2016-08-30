package com.cver.team.persistence.jena.objectMappers.dataObjectMappers;

import com.cver.team.model.data.Experience;
import com.cver.team.model.data.ProjectExperience;
import com.cver.team.persistence.jena.namespaces.CVO;
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

/**
 * Created by PC on 25/08/2016.
 */
public class ExperienceObjectMapper {
    public static <T extends Experience> T generateExperience(Model model, Resource resource, T experience) {
        experience = DataObjectMapper.generateData(model, resource, experience);

        // Address
        Statement statement = resource.getProperty(CVO.getProperty("address"));
        if (statement != null) {
            experience.setLocation(AddressObjectMapper.generateAddress(model, statement.getObject().asResource()));
        }

        // City
        statement = resource.getProperty(CVO.getProperty("city"));
        if (statement != null) {
            experience.setLocation(CityObjectMapper.generateCity(model, statement.getObject().asResource()));
        }

        // Country
        statement = resource.getProperty(CVO.getProperty("country"));
        if (statement != null) {
            experience.setLocation(CountryObjectMapper.generateCountry(model, statement.getObject().asResource()));
        }

        // Period
        statement = resource.getProperty(CVO.getProperty("period"));
        if (statement != null) {
            experience.setPeriod(PeriodObjectMapper.generatePeriod(model, statement.getObject().asResource()));
        }

        return experience;
    }

    public static Experience generateExperience(Model model, Resource resource) {
        if (resource.hasProperty(RDF.type, CVO.getResource("Education")))
            return EducationObjectMapper.generateEducationalExperience(model, resource);
        else if (resource.hasProperty(RDF.type, CVO.getResource("ProjectExperience")))
            return ProjectExperienceObjectMapper.generateProjectExperience(model, resource);
        else if (resource.hasProperty(RDF.type, CVO.getResource("WorkExperience")))
            return WorkExperienceObjectMapper.generateExactlyWorkExperience(model, resource);
        else return generateExperience(model, resource, new Experience());
    }
}
