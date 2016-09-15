package com.cver.team.persistence.jena.objectMappers.dataObjectMappers;

import com.cver.team.model.BaseEntity;
import com.cver.team.model.data.Data;
import com.cver.team.model.data.Experience;
import com.cver.team.model.data.Video;
import com.cver.team.model.data.WorkExperience;
import com.cver.team.model.data.date.DateOfBirth;
import com.cver.team.model.data.date.DateOfFoundation;
import com.cver.team.model.data.string.FirstName;
import com.cver.team.model.data.string.TelephoneNumber;
import com.cver.team.model.data.string.ValueProposition;
import com.cver.team.model.entity.Agent;
import com.cver.team.model.entity.Entity;
import com.cver.team.model.entity.Person;
import com.cver.team.persistence.jena.helper.DateTimeConverter;
import com.cver.team.persistence.jena.namespaces.CVO;
import com.cver.team.persistence.jena.namespaces.CVR;
import com.cver.team.persistence.jena.namespaces.LUC;
import com.cver.team.persistence.jena.objectMappers.BaseEntityObjectMapper;
import com.cver.team.persistence.jena.objectMappers.dataObjectMappers.dateObjectMappers.DateOfBirthObjectMapper;
import com.cver.team.persistence.jena.objectMappers.dataObjectMappers.dateObjectMappers.DateOfFoundationObjectMapper;
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
import com.cver.team.persistence.jena.objectMappers.entityObjectMappers.AgentObjectMapper;
import com.cver.team.persistence.jena.objectMappers.entityObjectMappers.OrganizationObjectMapper;
import com.cver.team.persistence.jena.objectMappers.entityObjectMappers.PersonObjectMapper;
import com.sun.org.apache.xpath.internal.operations.String;
import org.apache.jena.rdf.model.*;
import org.apache.jena.rdf.model.impl.StatementImpl;
import org.apache.jena.vocabulary.RDF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by PC on 17/08/2016.
 */
@Component
public class DataObjectMapper {
    @Autowired
    TelephoneNumberObjectMapper telephoneNumberObjectMapper;
    @Autowired
    EmailObjectMapper emailObjectMapper;
    @Autowired
    LocationObjectMapper locationObjectMapper;
    @Autowired
    CountryObjectMapper countryObjectMapper;
    @Autowired
    CityObjectMapper cityObjectMapper;
    @Autowired
    AddressObjectMapper addressObjectMapper;
    @Autowired
    ExperienceObjectMapper experienceObjectMapper;
    @Autowired
    DateOfBirthObjectMapper dateOfBirthObjectMapper;
    @Autowired
    FirstNameObjectMapper firstNameObjectMapper;
    @Autowired
    LastNameObjectMapper lastNameObjectMapper;
    @Autowired
    ValuePropositionObjectMapper valuePropositionObjectMapper;
    @Autowired
    SkillObjectMapper skillObjectMapper;
    @Autowired
    CertificateObjectMapper certificateObjectMapper;
    @Autowired
    DateOfFoundationObjectMapper dateOfFoundationObjectMapper;
    @Autowired
    ImageObjectMapper imageObjectMapper;
    @Autowired
    VideoObjectMapper videoObjectMapper;
    @Autowired
    PersonObjectMapper personObjectMapper;
    @Autowired
    OrganizationObjectMapper organizationObjectMapper;
    @Autowired
    AgentObjectMapper agentObjectMapper;
    @Autowired
    BaseEntityObjectMapper baseEntityObjectMapper;

    public <T extends Data> T generateData(Model model, Resource resource, T data) {

        data = baseEntityObjectMapper.generateBaseEntity(model, resource, data);

        // Name
        Statement statement = resource.getProperty(CVO.getProperty("name"));
        if (statement != null)
            data.setName(statement.getObject().asLiteral().getString());

        statement = resource.getProperty(CVO.getProperty("creationDate"));
        if (statement != null)
            data.setCreationDate(DateTimeConverter.getDate(statement.getObject()));

        statement = resource.getProperty(CVO.getProperty("lastModified"));
        if (statement != null)
            data.setLastModified(DateTimeConverter.getDate(statement.getObject()));

        StmtIterator owners = resource.listProperties(CVO.getProperty("owner"));
        while (owners.hasNext()) {
            statement = owners.nextStatement();
            Resource owner = statement.getObject().asResource();
            if (owner.hasProperty(RDF.type, CVO.getResource("Person")))
                data.addOwner(personObjectMapper.generatePerson(model, owner));
            else data.addOwner(organizationObjectMapper.generateOrganization(model, owner));
        }

        statement = resource.getProperty(CVO.getProperty("val"));
        if (statement != null) {
            Literal val = statement.getObject().asLiteral();
                data.setValue(val.getString());
        }

        return data;
    }

    public Data generateData(Model model, Resource resource) {
        if (resource.hasProperty(RDF.type, CVO.getResource("Certificate")))
            return certificateObjectMapper.generateCertificate(model, resource);
        else if (resource.hasProperty(RDF.type, CVO.getResource("FirstName")))
            return firstNameObjectMapper.generateFirstName(model, resource);
        else if (resource.hasProperty(RDF.type, CVO.getResource("LastName")))
            return lastNameObjectMapper.generateLastName(model, resource);
        else if (resource.hasProperty(RDF.type, CVO.getResource("ValueProposition")))
            return valuePropositionObjectMapper.generateValueProposition(model, resource);
        else if (resource.hasProperty(RDF.type, CVO.getResource("TelephoneNumber")))
            return telephoneNumberObjectMapper.generateTelephoneNumber(model, resource);
        else if (resource.hasProperty(RDF.type, CVO.getResource("DateOfBirth")))
            return dateOfBirthObjectMapper.generateDate(model, resource);
        else if (resource.hasProperty(RDF.type, CVO.getResource("DateOfFoundation")))
            return dateOfFoundationObjectMapper.generateDate(model, resource);
        else if (resource.hasProperty(RDF.type, CVO.getResource("Address")))
            return addressObjectMapper.generateAddress(model, resource);
        else if (resource.hasProperty(RDF.type, CVO.getResource("City")))
            return cityObjectMapper.generateCity(model, resource);
        else if (resource.hasProperty(RDF.type, CVO.getResource("Country")))
            return countryObjectMapper.generateCountry(model, resource);
        else if (resource.hasProperty(RDF.type, CVO.getResource("Address")))
            return addressObjectMapper.generateAddress(model, resource);
        else if (resource.hasProperty(RDF.type, CVO.getResource("Email")))
            return emailObjectMapper.generateEmail(model, resource);
        else if (resource.hasProperty(RDF.type, CVO.getResource("Experience")))
            return experienceObjectMapper.generateExperience(model, resource);
        else if (resource.hasProperty(RDF.type, CVO.getResource("Image")))
            return imageObjectMapper.generateImage(model, resource);
        else if (resource.hasProperty(RDF.type, CVO.getResource("Video")))
            return videoObjectMapper.generateVideo(model, resource);
        else if (resource.hasProperty(RDF.type, CVO.getResource("Skill")))
            return skillObjectMapper.generateSkill(model, resource);
        return null;
    }

    public List<Data> generateDataP(Model model) {
        Map<Double, Data> map = new TreeMap<>(new Comparator<Double>() {
            @Override
            public int compare(Double o1, Double o2) {
                return o2.compareTo(o1);
            }
        });
        ResIterator iterator = model.listResourcesWithProperty(LUC.getProperty("score"));
        while(iterator.hasNext()) {
            Resource resource = iterator.next();
            Double score = resource.getProperty(LUC.getProperty("score")).getObject().asLiteral().getDouble();
            map.put(score, generateData(model, resource));
        }
        return new ArrayList<>(map.values());
    }

    public <T extends Data> void createModel(T data, Model model, Resource resource) {
        baseEntityObjectMapper.createMode(data, model ,resource);

        // Create current date
        java.util.Date date = new java.util.Date();
        Calendar calendar = DateTimeConverter.getCalendar(date);
        Literal dateTime = model.createTypedLiteral(calendar);

        // Set creation and last modified dates
        model.add(new StatementImpl(resource, CVO.getProperty("creationDate"), dateTime));
        model.add(new StatementImpl(resource, CVO.getProperty("lastModified"), dateTime));

        if (data.getValue() != null) {
            Literal valLiteral = model.createLiteral(data.getValue());
            model.add(new StatementImpl(resource, CVO.getProperty("val"), valLiteral));
        }

        for (Agent owner : data.getOwners()) {
            if (owner.getIdentifier() == null)
                agentObjectMapper.createModel(owner, model);
            model.add(new StatementImpl(resource, CVO.getProperty("owner"), CVR.getResource(owner.getIdentifier().getId())));
        }
    }
}
