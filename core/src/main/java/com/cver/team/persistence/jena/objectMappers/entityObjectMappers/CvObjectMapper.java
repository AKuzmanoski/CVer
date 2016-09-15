package com.cver.team.persistence.jena.objectMappers.entityObjectMappers;

import com.cver.team.model.data.*;
import com.cver.team.model.data.string.TelephoneNumber;
import com.cver.team.model.entity.CV;
import com.cver.team.persistence.jena.helper.IdentifierGenerator;
import com.cver.team.persistence.jena.namespaces.CVO;
import com.cver.team.persistence.jena.namespaces.CVR;
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
import com.cver.team.persistence.jena.objectMappers.dataObjectMappers.stringObjectMappers.ValuePropositionObjectMapper;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.rdf.model.impl.StatementImpl;
import org.apache.jena.vocabulary.RDF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by PC on 25/08/2016.
 */
@Component
public class CvObjectMapper {
    @Autowired
    EmailObjectMapper emailObjectMapper;
    @Autowired
    TelephoneNumberObjectMapper telephoneNumberObjectMapper;
    @Autowired
    AddressObjectMapper addressObjectMapper;
    @Autowired
    CityObjectMapper cityObjectMapper;
    @Autowired
    CountryObjectMapper countryObjectMapper;
    @Autowired
    LocationObjectMapper locationObjectMapper;
    @Autowired
    FirstNameObjectMapper firstNameObjectMapper;
    @Autowired
    LastNameObjectMapper lastNameObjectMapper;
    @Autowired
    ValuePropositionObjectMapper valuePropositionObjectMapper;
    @Autowired
    DateOfBirthObjectMapper dateOfBirthObjectMapper;
    @Autowired
    ExperienceObjectMapper experienceObjectMapper;
    @Autowired
    SkillObjectMapper skillObjectMapper;
    @Autowired
    DocumentObjectMapper documentObjectMapper;
    @Autowired
    CertificateObjectMapper certificateObjectMapper;
    @Autowired
    ProjectExperienceObjectMapper projectExperienceObjectMapper;
    @Autowired
    EducationObjectMapper educationObjectMapper;
    @Autowired
    WorkExperienceObjectMapper workExperienceObjectMapper;
    @Autowired
    ImageObjectMapper imageObjectMapper;
    @Autowired
    TemplateObjectMapper templateObjectMapper;

    public CV generateCv(Model model, Resource resource) {
        CV cv = new CV();
        cv = documentObjectMapper.generateDocument(model, resource, cv);

        // Date of birth
        Statement statement = resource.getProperty(CVO.getProperty("dateOfBirth"));
        if (statement != null) {
            cv.setDateOfBirth(dateOfBirthObjectMapper.generateDate(model, statement.getObject().asResource()));
        }

        // Image
        statement = resource.getProperty(CVO.getProperty("image"));
        if (statement != null) {
            cv.setImage(imageObjectMapper.generateImage(model, statement.getObject().asResource()));
        }

        // FirstName
        statement = resource.getProperty(CVO.getProperty("firstName"));
        if (statement != null) {
            cv.setFirstName(firstNameObjectMapper.generateFirstName(model, statement.getObject().asResource()));
        }

        // LastName
        statement = resource.getProperty(CVO.getProperty("lastName"));
        if (statement != null) {
            cv.setLastName(lastNameObjectMapper.generateLastName(model, statement.getObject().asResource()));
        }

        // Telephone numbers
        StmtIterator stmtIterator = resource.listProperties(CVO.getProperty("telephoneNumber"));
        while (stmtIterator.hasNext()) {
            cv.addTelephoneNumber(telephoneNumberObjectMapper.generateTelephoneNumber(model, stmtIterator.next().getObject().asResource()));
        }

        // Emails
        stmtIterator = resource.listProperties(CVO.getProperty("email"));
        while (stmtIterator.hasNext()) {
            cv.addEmail(emailObjectMapper.generateEmail(model, stmtIterator.next().getObject().asResource()));
        }

        // Addresses
        stmtIterator = resource.listProperties(CVO.getProperty("address"));
        while (stmtIterator.hasNext()) {
            cv.addLocation(addressObjectMapper.generateAddress(model, stmtIterator.next().getObject().asResource()));
        }

        // Cities
        stmtIterator = resource.listProperties(CVO.getProperty("city"));
        while (stmtIterator.hasNext()) {
            cv.addLocation(cityObjectMapper.generateCity(model, stmtIterator.next().getObject().asResource()));
        }

        // Countries
        stmtIterator = resource.listProperties(CVO.getProperty("country"));
        while (stmtIterator.hasNext()) {
            cv.addLocation(countryObjectMapper.generateCountry(model, stmtIterator.next().getObject().asResource()));
        }

        // Experiences
        stmtIterator = resource.listProperties(CVO.getProperty("experience"));
        while (stmtIterator.hasNext()) {
            cv.addExperience(experienceObjectMapper.generateExperience(model, stmtIterator.next().getObject().asResource()));
        }

        // Project experiences
        stmtIterator = resource.listProperties(CVO.getProperty("projectExperience"));
        while (stmtIterator.hasNext()) {
            cv.addProjectExperience(projectExperienceObjectMapper.generateProjectExperience(model, stmtIterator.next().getObject().asResource()));
        }

        // Education
        stmtIterator = resource.listProperties(CVO.getProperty("education"));
        while (stmtIterator.hasNext()) {
            cv.addEducation(educationObjectMapper.generateEducationalExperience(model, stmtIterator.next().getObject().asResource()));
        }

        // Work Experiences
        stmtIterator = resource.listProperties(CVO.getProperty("workExperience"));
        while (stmtIterator.hasNext()) {
            cv.addWorkExperience(workExperienceObjectMapper.generateWorkExperience(model, stmtIterator.next().getObject().asResource()));
        }

        // Skills
        stmtIterator = resource.listProperties(CVO.getProperty("skill"));
        while (stmtIterator.hasNext()) {
            cv.addSkill(skillObjectMapper.generateSkill(model, stmtIterator.next().getObject().asResource()));
        }

        // Certificates
        stmtIterator = resource.listProperties(CVO.getProperty("certificate"));
        while (stmtIterator.hasNext()) {
            cv.addCertificate(certificateObjectMapper.generateCertificate(model, stmtIterator.next().getObject().asResource()));
        }

        return cv;
    }

    public CV generateCv(Model model, String uri) {
        Resource resource = model.getResource(uri);
        if (resource != null)
            return generateCv(model, resource);
        return null;
    }

    public void createModel(CV cv, Model model) {
        if (cv.getIdentifier() != null)
            return;
        cv.setIdentifier(IdentifierGenerator.generateIdentifier());
        Resource cvResource = model.createResource(cv.getIdentifier().getURI());
        cv.setCoverPictureUrl(cv.getTemplate().getCoverPictureUrl());

        createModel(cv, model, cvResource);
    }

    public void createModel(CV cv, Model model, Resource cvResource) {
        documentObjectMapper.createModel(cv, model, cvResource);

        model.add(new StatementImpl(cvResource, RDF.type, CVO.getResource("CV")));
        if (cv.getFirstName() != null) {
            if (cv.getFirstName().getIdentifier() == null)
                firstNameObjectMapper.createModel(cv.getFirstName(), model);
            model.add(new StatementImpl(cvResource, CVO.getProperty("firstName"), CVR.getResource(cv.getFirstName().getIdentifier().getId())));
        }
        if (cv.getLastName() != null) {
            if (cv.getLastName().getIdentifier() == null)
                lastNameObjectMapper.createModel(cv.getLastName(), model);
            model.add(new StatementImpl(cvResource, CVO.getProperty("lastName"), CVR.getResource(cv.getLastName().getIdentifier().getId())));
        }
        if (cv.getDateOfBirth() != null) {
            if (cv.getDateOfBirth().getIdentifier() == null)
                dateOfBirthObjectMapper.createModel(cv.getDateOfBirth(), model);
            model.add(new StatementImpl(cvResource, CVO.getProperty("dateOfBirth"), CVR.getResource(cv.getDateOfBirth().getIdentifier().getId())));
        }
        for (Email email : cv.getEmails()) {
            if (email.getIdentifier() == null)
                emailObjectMapper.createModel(email, model);
            model.add(new StatementImpl(cvResource, CVO.getProperty("email"), CVR.getResource(email.getIdentifier().getId())));
        }
        for (TelephoneNumber telephoneNumber : cv.getTelephoneNumbers()) {
            if (telephoneNumber.getIdentifier() == null)
                telephoneNumberObjectMapper.createModel(telephoneNumber, model);
            model.add(new StatementImpl(cvResource, CVO.getProperty("telephoneNumber"), CVR.getResource(telephoneNumber.getIdentifier().getId())));
        }
        for (Location location : cv.getLocations()) {
            if (location.getIdentifier() == null)
                locationObjectMapper.createModel(location, model);
            if (location instanceof Address) {
                model.add(new StatementImpl(cvResource, CVO.getProperty("address"), CVR.getResource(location.getIdentifier().getId())));
            } else if (location instanceof City) {
                model.add(new StatementImpl(cvResource, CVO.getProperty("city"), CVR.getResource(location.getIdentifier().getId())));
            } else if (location instanceof Country) {
                model.add(new StatementImpl(cvResource, CVO.getProperty("country"), CVR.getResource(location.getIdentifier().getId())));
            } else {
                model.add(new StatementImpl(cvResource, CVO.getProperty("location"), CVR.getResource(location.getIdentifier().getId())));
            }
        }
        for (Certificate certificate : cv.getCertificates()) {
            certificateObjectMapper.createModel(certificate, model);
            model.add(new StatementImpl(cvResource, CVO.getProperty("certificate"), CVR.getResource(certificate.getIdentifier().getId())));
        }
        for (Skill skill : cv.getSkills()) {
            if (skill.getIdentifier() == null)
                skillObjectMapper.createModel(skill, model);
            model.add(new StatementImpl(cvResource, CVO.getProperty("skill"), CVR.getResource(skill.getIdentifier().getId())));
        }
        for (Experience experience : cv.getExperiences()) {
            experienceObjectMapper.createModel(experience, model);
            model.add(new StatementImpl(cvResource, CVO.getProperty("experience"), CVR.getResource(experience.getIdentifier().getId())));
        }
        for (ProjectExperience projectExperience : cv.getProjectExperiences()) {
            projectExperienceObjectMapper.createModel(projectExperience, model);
            model.add(new StatementImpl(cvResource, CVO.getProperty("projectExperience"), CVR.getResource(projectExperience.getIdentifier().getId())));
        }
        for (WorkExperience workExperience : cv.getWorkExperiences()) {
            workExperienceObjectMapper.createModel(workExperience, model);
            model.add(new StatementImpl(cvResource, CVO.getProperty("workExperience"), CVR.getResource(workExperience.getIdentifier().getId())));
        }
        for (EducationalExperience educationalExperience : cv.getEducations()) {
            educationObjectMapper.createModel(educationalExperience, model);
            model.add(new StatementImpl(cvResource, CVO.getProperty("education"), CVR.getResource(educationalExperience.getIdentifier().getId())));
        }

        templateObjectMapper.createModel(cv.getTemplate(), model);
        model.add(cvResource, CVO.getProperty("template"), CVR.getResource(cv.getTemplate().getIdentifier().getId()));
    }

    public void updateModel(CV oldCv, CV newCv, Model insert, Model delete) {
        templateObjectMapper.createModel(newCv.getTemplate(), insert);
        if (!oldCv.getTemplate().equals(newCv.getTemplate())) {
            delete.add(new StatementImpl(CVR.getResource(oldCv.getIdentifier().getId()), CVO.getProperty("template"), CVR.getResource(oldCv.getTemplate().getIdentifier().getId())));
            insert.add(new StatementImpl(CVR.getResource(newCv.getIdentifier().getId()), CVO.getProperty("template"), CVR.getResource(newCv.getTemplate().getIdentifier().getId())));
        }

        if (newCv.getFirstName() != null) {
            firstNameObjectMapper.createModel(newCv.getFirstName(), insert);
            if (!newCv.getFirstName().equals(oldCv.getFirstName()))
                insert.add(new StatementImpl(CVR.getResource(newCv.getIdentifier().getId()), CVO.getProperty("firstName"), CVR.getResource(newCv.getFirstName().getIdentifier().getId())));
            if (oldCv.getFirstName() != null)
                delete.add(new StatementImpl(CVR.getResource(newCv.getIdentifier().getId()), CVO.getProperty("firstName"), CVR.getResource(oldCv.getFirstName().getIdentifier().getId())));
        }

        if (newCv.getLastName() != null) {
            lastNameObjectMapper.createModel(newCv.getLastName(), insert);
            if (!newCv.getLastName().equals(oldCv.getLastName()))
                insert.add(new StatementImpl(CVR.getResource(newCv.getIdentifier().getId()), CVO.getProperty("lastName"), CVR.getResource(newCv.getLastName().getIdentifier().getId())));
            if (oldCv.getLastName() != null)
                delete.add(new StatementImpl(CVR.getResource(newCv.getIdentifier().getId()), CVO.getProperty("lastName"), CVR.getResource(oldCv.getLastName().getIdentifier().getId())));
        }

        if (newCv.getDateOfBirth() != null) {
            dateOfBirthObjectMapper.createModel(newCv.getDateOfBirth(), insert);
            if (!newCv.getDateOfBirth().equals(oldCv.getDateOfBirth()))
                insert.add(new StatementImpl(CVR.getResource(newCv.getIdentifier().getId()), CVO.getProperty("dateOfBirth"), CVR.getResource(newCv.getDateOfBirth().getIdentifier().getId())));
            if (oldCv.getDateOfBirth() != null)
                delete.add(new StatementImpl(CVR.getResource(newCv.getIdentifier().getId()), CVO.getProperty("dateOfBirth"), CVR.getResource(oldCv.getDateOfBirth().getIdentifier().getId())));
        }
    }
}
