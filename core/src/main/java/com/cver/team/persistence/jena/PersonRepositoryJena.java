package com.cver.team.persistence.jena;


import com.cver.team.model.entity.Person;
import com.cver.team.model.literal.Identifier;
import com.cver.team.persistence.PersonRepository;
import com.cver.team.persistence.helper.MailEncoder;
import com.cver.team.persistence.helper.URIMaker;
import com.cver.team.persistence.jena.helper.DateTimeConverter;
import com.cver.team.persistence.jena.helper.JenaPreferences;
import com.cver.team.persistence.jena.namespaces.CVR;
import com.cver.team.persistence.jena.objectMappers.entityObjectMappers.PersonObjectMapper;
import com.cver.team.persistence.jena.queries.Queries;
import org.apache.jena.assembler.Mode;
import org.apache.jena.datatypes.xsd.XSDDatatype;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.update.UpdateExecutionFactory;
import org.apache.jena.update.UpdateProcessor;
import org.apache.jena.update.UpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;


@Repository
public class PersonRepositoryJena implements PersonRepository {
    @Autowired
    QueryRepository queryRepository;
    @Autowired
    MailEncoder mailEncoder;
    @Autowired
    PersonObjectMapper personObjectMapper;

    @Override
    public Person savePerson(Person person) {
        if (person.getIdentifier() == null)
            return insertPerson(person);
        return updatePerson(person);
    }

    public Person updatePerson(Person person) {
        return person;
    }

    public Person insertPerson(Person person) {
        Identifier identifier = new Identifier();
        String id = URIMaker.generateUri();
        identifier.setId(id);
        identifier.setURI(CVR.getURI(id));
        person.setIdentifier(identifier);

        Date date = new Date();

        ParameterizedSparqlString queryString = new ParameterizedSparqlString();
        queryString.setCommandText(queryRepository.getQuery(Queries.insertPerson));

        queryString.setIri("person", person.getIdentifier().getURI());
        queryString.setIri("account", CVR.generateURI());
        queryString.setIri("defaultFirstName", CVR.generateURI());
        queryString.setIri("defaultLastName", CVR.generateURI());
        queryString.setLiteral("creationDate", DateTimeConverter.getCalendar(date));
        queryString.setLiteral("lastModified", DateTimeConverter.getCalendar(date));
        if (person.getProfilePictureUrl() != null) {
            queryString.setIri("profilePicture", CVR.generateURI());
            queryString.setLiteral("profilePictureUrl", person.getProfilePictureUrl(),  XSDDatatype.XSDstring);
            queryString.setLiteral("profilePictureCreationDate", DateTimeConverter.getCalendar(date));
            queryString.setLiteral("profilePictureLastModified", DateTimeConverter.getCalendar(date));
        }
        if (person.getCoverPictureUrl() != null) {
            queryString.setIri("cover", CVR.generateURI());
            queryString.setLiteral("coverUrl", person.getCoverPictureUrl(),  XSDDatatype.XSDstring);
            queryString.setLiteral("coverCreationDate", DateTimeConverter.getCalendar(date));
            queryString.setLiteral("coverLastModified", DateTimeConverter.getCalendar(date));
        }

        // Set account values
        queryString.setLiteral("provider", person.getProvider().name(), XSDDatatype.XSDstring);
        queryString.setLiteral("role", person.getRole().name(), XSDDatatype.XSDstring);
        if (person.getPassword() != null)
            queryString.setLiteral("password", person.getPassword(), XSDDatatype.XSDstring);
        queryString.setIri("loginEmail", CVR.generateURI());
        queryString.setLiteral("mbox", person.getEmail(), XSDDatatype.XSDstring);
        queryString.setLiteral("hashedMbox", mailEncoder.encode(person.getEmail()), XSDDatatype.XSDstring);
        queryString.setLiteral("emailCreationDate", DateTimeConverter.getCalendar(date));
        queryString.setLiteral("emailLastModified", DateTimeConverter.getCalendar(date));

        // Set person values
        queryString.setLiteral("firstNameVal", person.getFirstName());
        queryString.setLiteral("firstNameCreationDate", DateTimeConverter.getCalendar(date));
        queryString.setLiteral("firstNameLastModified", DateTimeConverter.getCalendar(date));
        queryString.setLiteral("lastNameVal", person.getLastName());
        queryString.setLiteral("lastNameCreationDate", DateTimeConverter.getCalendar(date));
        queryString.setLiteral("lastNameLastModified", DateTimeConverter.getCalendar(date));
        if (person.getName() != null)
            queryString.setLiteral("name", person.getName());
        if (person.getDescription() != null)
            queryString.setLiteral("description", person.getDescription());

        UpdateRequest updateRequest = queryString.asUpdate();
        UpdateProcessor updateProcessor = UpdateExecutionFactory.createRemoteForm(updateRequest, JenaPreferences.UpdateEndpoint);
        updateProcessor.execute();

        return person;
    }

    @Override
    public boolean isEmailTaken(String email) {
        ParameterizedSparqlString queryString = new ParameterizedSparqlString();
        queryString.setCommandText(queryRepository.getQuery(Queries.isEmailTaken));
        queryString.setLiteral("email", email);

        Query query = queryString.asQuery();
        QueryExecution queryExecution = QueryExecutionFactory.sparqlService(JenaPreferences.SPARQLEndpoint, query);
        return queryExecution.execAsk();
    }

    @Override
    public Person getPersonByLoginEmail(String email) {
        ParameterizedSparqlString queryString = new ParameterizedSparqlString();
        queryString.setCommandText(queryRepository.getQuery(Queries.getPerson));
        queryString.setLiteral("email", email, XSDDatatype.XSDstring);

        Query query = queryString.asQuery();
        QueryExecution queryExecution = QueryExecutionFactory.sparqlService(JenaPreferences.SPARQLEndpoint, query);
        Model model = queryExecution.execConstruct();
        Person person = personObjectMapper.generatePerson(model);
        return person;
    }

    @Override
    public Person getPersonById(String id) {
        ParameterizedSparqlString queryString = new ParameterizedSparqlString();
        queryString.setCommandText(queryRepository.getQuery(Queries.getPerson));
        queryString.setIri("person", CVR.getURI(id));

        Query query = queryString.asQuery();
        QueryExecution queryExecution = QueryExecutionFactory.sparqlService(JenaPreferences.SPARQLEndpoint, query);
        Model model = queryExecution.execConstruct();
        Person person = personObjectMapper.generatePerson(model);
        return person;
    }

    @Override
    public void watchLater(String id, String entityId) {
        ParameterizedSparqlString queryString = new ParameterizedSparqlString();
        queryString.setCommandText(queryRepository.getQuery(Queries.watchLater));
        queryString.setIri("person", CVR.getURI(id));
        queryString.setIri("entity", CVR.getURI(entityId));

        System.out.println(queryString.toString());
        UpdateRequest updateRequest = queryString.asUpdate();
        UpdateProcessor updateProcessor = UpdateExecutionFactory.createRemoteForm(updateRequest, JenaPreferences.UpdateEndpoint);
        updateProcessor.execute();
    }

    @Override
    public Person deletePerson(Person person) {
        return null;
    }
}
