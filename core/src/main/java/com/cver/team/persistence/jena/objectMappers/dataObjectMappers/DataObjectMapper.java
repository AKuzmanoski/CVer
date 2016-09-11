package com.cver.team.persistence.jena.objectMappers.dataObjectMappers;

import com.cver.team.model.BaseEntity;
import com.cver.team.model.data.Data;
import com.cver.team.model.data.Experience;
import com.cver.team.model.data.WorkExperience;
import com.cver.team.model.data.date.DateOfFoundation;
import com.cver.team.model.data.string.FirstName;
import com.cver.team.model.data.string.TelephoneNumber;
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

import java.util.*;

/**
 * Created by PC on 17/08/2016.
 */
public class DataObjectMapper {
    public static <T extends Data> T generateData(Model model, Resource resource, T data) {

        data = BaseEntityObjectMapper.generateBaseEntity(model, resource, data);

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
                data.addOwner(PersonObjectMapper.generatePerson(model, owner));
            else data.addOwner(OrganizationObjectMapper.generateOrganization(model, owner));
        }

        statement = resource.getProperty(CVO.getProperty("val"));
        if (statement != null) {
            Literal val = statement.getObject().asLiteral();
                data.setValue(val.getString());
        }

        return data;
    }

    public static Data generateData(Model model, Resource resource) {
        if (resource.hasProperty(RDF.type, CVO.getResource("Certificate")))
            return CertificateObjectMapper.generateCertificate(model, resource);
        else if (resource.hasProperty(RDF.type, CVO.getResource("FirstName")))
            return FirstNameObjectMapper.generateFirstName(model, resource);
        else if (resource.hasProperty(RDF.type, CVO.getResource("LastName")))
            return LastNameObjectMapper.generateLastName(model, resource);
        else if (resource.hasProperty(RDF.type, CVO.getResource("ValueProposition")))
            return ValuePropositionObjectMapper.generateValueProposition(model, resource);
        else if (resource.hasProperty(RDF.type, CVO.getResource("TelephoneNumber")))
            return TelephoneNumberObjectMapper.generateTelephoneNumber(model, resource);
        else if (resource.hasProperty(RDF.type, CVO.getResource("DateOfBirth")))
            return DateOfBirthObjectMapper.generateDate(model, resource);
        else if (resource.hasProperty(RDF.type, CVO.getResource("DateOfFoundation")))
            return DateOfFoundationObjectMapper.generateDate(model, resource);
        else if (resource.hasProperty(RDF.type, CVO.getResource("Address")))
            return AddressObjectMapper.generateAddress(model, resource);
        else if (resource.hasProperty(RDF.type, CVO.getResource("City")))
            return CityObjectMapper.generateCity(model, resource);
        else if (resource.hasProperty(RDF.type, CVO.getResource("Country")))
            return CountryObjectMapper.generateCountry(model, resource);
        else if (resource.hasProperty(RDF.type, CVO.getResource("Address")))
            return CountryObjectMapper.generateCountry(model, resource);
        else if (resource.hasProperty(RDF.type, CVO.getResource("Email")))
            return EmailObjectMapper.generateEmail(model, resource);
        else if (resource.hasProperty(RDF.type, CVO.getResource("Experience")))
            return ExperienceObjectMapper.generateExperience(model, resource);
        else if (resource.hasProperty(RDF.type, CVO.getResource("Image")))
            return ImageObjectMapper.generateImage(model, resource);
        else if (resource.hasProperty(RDF.type, CVO.getResource("Video")))
            return VideoObjectMapper.generateVideo(model, resource);
        else if (resource.hasProperty(RDF.type, CVO.getResource("Skill")))
            return SkillObjectMapper.generateSkill(model, resource);
        return null;
    }

    public static List<Data> generateDataP(Model model) {
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

    public static <T extends Data> void createModel(T data, Model model, Resource resource) {
        BaseEntityObjectMapper.createMode(data, model ,resource);

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
                AgentObjectMapper.createModel(owner, model);
            model.add(new StatementImpl(resource, CVO.getProperty("owner"), CVR.getResource(owner.getIdentifier().getId())));
        }
    }
}
