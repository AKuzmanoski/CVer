package com.cver.team.persistence.jena.objectMappers.entityObjectMappers;

import com.cver.team.model.entity.CV;
import com.cver.team.persistence.jena.namespaces.CVO;
import com.cver.team.persistence.jena.objectMappers.dataObjectMappers.*;
import com.cver.team.persistence.jena.objectMappers.dataObjectMappers.dateObjectMappers.DateOfBirthObjectMapper;
import com.cver.team.persistence.jena.objectMappers.dataObjectMappers.experienceObjectMappers.EducationObjectMapper;
import com.cver.team.persistence.jena.objectMappers.dataObjectMappers.experienceObjectMappers.ProjectExperienceObjectMapper;
import com.cver.team.persistence.jena.objectMappers.dataObjectMappers.experienceObjectMappers.WorkExperienceObjectMapper;
import com.cver.team.persistence.jena.objectMappers.dataObjectMappers.locatonObjectMappers.AddressObjectMapper;
import com.cver.team.persistence.jena.objectMappers.dataObjectMappers.locatonObjectMappers.CityObjectMapper;
import com.cver.team.persistence.jena.objectMappers.dataObjectMappers.locatonObjectMappers.CountryObjectMapper;
import com.cver.team.persistence.jena.objectMappers.dataObjectMappers.stringObjectMappers.FirstNameObjectMapper;
import com.cver.team.persistence.jena.objectMappers.dataObjectMappers.stringObjectMappers.LastNameObjectMapper;
import com.cver.team.persistence.jena.objectMappers.dataObjectMappers.stringObjectMappers.TelephoneNumberObjectMapper;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;

/**
 * Created by PC on 25/08/2016.
 */
public class CvObjectMapper {
    public static CV generateCv(Model model, Resource resource) {
        CV cv = new CV();
        cv = DocumentObjectMapper.generateDocument(model, resource, cv);

        // Date of birth
        Statement statement = resource.getProperty(CVO.getProperty("dateOfBirth"));
        if (statement != null) {
            cv.setDateOfBirth(DateOfBirthObjectMapper.generateDate(model, statement.getObject().asResource()));
        }

        // Image
        statement = resource.getProperty(CVO.getProperty("image"));
        if (statement != null) {
            cv.setImage(ImageObjectMapper.generateImage(model, statement.getObject().asResource()));
        }

        // FirstName
        statement = resource.getProperty(CVO.getProperty("firstName"));
        if (statement != null) {
            cv.setFirstName(FirstNameObjectMapper.generateFirstName(model, statement.getObject().asResource()));
        }

        // LastName
        statement = resource.getProperty(CVO.getProperty("lastName"));
        if (statement != null) {
            cv.setLastName(LastNameObjectMapper.generateLastName(model, statement.getObject().asResource()));
        }

        // Telephone numbers
        StmtIterator stmtIterator = resource.listProperties(CVO.getProperty("telephoneNumber"));
        while (stmtIterator.hasNext()) {
            cv.addTelephoneNumber(TelephoneNumberObjectMapper.generateTelephoneNumber(model, stmtIterator.next().getObject().asResource()));
        }

        // Emails
        stmtIterator = resource.listProperties(CVO.getProperty("email"));
        while (stmtIterator.hasNext()) {
            cv.addEmail(EmailObjectMapper.generateEmail(model, stmtIterator.next().getObject().asResource()));
        }

        // Addresses
        stmtIterator = resource.listProperties(CVO.getProperty("address"));
        while (stmtIterator.hasNext()) {
            cv.addLocation(AddressObjectMapper.generateAddress(model, stmtIterator.next().getObject().asResource()));
        }

        // Cities
        stmtIterator = resource.listProperties(CVO.getProperty("city"));
        while (stmtIterator.hasNext()) {
            cv.addLocation(CityObjectMapper.generateCity(model, stmtIterator.next().getObject().asResource()));
        }

        // Countries
        stmtIterator = resource.listProperties(CVO.getProperty("country"));
        while (stmtIterator.hasNext()) {
            cv.addLocation(CountryObjectMapper.generateCountry(model, stmtIterator.next().getObject().asResource()));
        }

        // Experiences
        stmtIterator = resource.listProperties(CVO.getProperty("experience"));
        while (stmtIterator.hasNext()) {
            cv.addExperience(ExperienceObjectMapper.generateExperience(model, stmtIterator.next().getObject().asResource()));
        }

        // Project experiences
        stmtIterator = resource.listProperties(CVO.getProperty("projectExperience"));
        while (stmtIterator.hasNext()) {
            cv.addProjectExperience(ProjectExperienceObjectMapper.generateProjectExperience(model, stmtIterator.next().getObject().asResource()));
        }

        // Education
        stmtIterator = resource.listProperties(CVO.getProperty("education"));
        while (stmtIterator.hasNext()) {
            cv.addEducation(EducationObjectMapper.generateEducationalExperience(model, stmtIterator.next().getObject().asResource()));
        }

        // Work Experiences
        stmtIterator = resource.listProperties(CVO.getProperty("workExperience"));
        while (stmtIterator.hasNext()) {
            cv.addWorkExperience(WorkExperienceObjectMapper.generateWorkExperience(model, stmtIterator.next().getObject().asResource()));
        }

        // Skills
        stmtIterator = resource.listProperties(CVO.getProperty("skill"));
        while (stmtIterator.hasNext()) {
            cv.addSkill(SkillObjectMapper.generateSkill(model, stmtIterator.next().getObject().asResource()));
        }

        // Certificates
        stmtIterator = resource.listProperties(CVO.getProperty("certificate"));
        while (stmtIterator.hasNext()) {
            cv.addCertificate(CertificateObjectMapper.generateCertificate(model, stmtIterator.next().getObject().asResource()));
        }

        return cv;
    }

    public static CV generateCv(Model model, String uri) {
        Resource resource = model.getResource(uri);
        if (resource != null)
            return generateCv(model, resource);
        return null;
    }
}
