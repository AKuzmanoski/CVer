package com.cver.team.persistence.jena;

import com.cver.team.model.Person;
import com.cver.team.model.literal.Identifier;
import com.cver.team.persistence.PersonRepository;
import com.cver.team.persistence.helper.URIMaker;
import com.cver.team.persistence.jena.helper.JenaPreferences;
import com.cver.team.persistence.jena.helper.ResourcePrefixes;
import com.cver.team.persistence.jena.namespaces.CVR;
import com.cver.team.persistence.jena.objectMappers.PersonObjectMapper;
import com.cver.team.persistence.jena.queries.Queries;
import org.apache.jena.datatypes.xsd.XSDDatatype;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.update.UpdateExecutionFactory;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateProcessor;
import org.apache.jena.update.UpdateRequest;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public class PersonRepositoryJena implements PersonRepository {
    @Autowired
    QueryRepository queryRepository;

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


        ParameterizedSparqlString queryString = new ParameterizedSparqlString();
        queryString.setCommandText(queryRepository.getQuery(Queries.insertPerson));

        queryString.setIri("person", person.getIdentifier().getURI());
        queryString.setIri("account", CVR.generateURI());
        queryString.setIri("defaultFirstName", CVR.generateURI());
        queryString.setIri("defaultLastName", CVR.generateURI());

        // Set account values
        queryString.setLiteral("provider", person.getProvider().toString(), XSDDatatype.XSDstring);
        queryString.setLiteral("role", person.getRole().toString(), XSDDatatype.XSDstring);
        if (person.getPassword() != null)
            queryString.setLiteral("password", person.getPassword());
        queryString.setIri("loginEmail", CVR.generateURI());
        queryString.setLiteral("email", person.getEmail());
        queryString.setLiteral("hashedEmail", MD5Encoder.encode(person.getEmail().getBytes()));

        // Set person values
        queryString.setLiteral("firstName", person.getFirstName());
        queryString.setLiteral("lastName", person.getLastName());

        UpdateRequest updateRequest = queryString.asUpdate();
        UpdateProcessor updateProcessor = UpdateExecutionFactory.createRemote(updateRequest, JenaPreferences.UpdateEndpoint);
        updateProcessor.execute();

        return person;
    }

    @Override
    public boolean isEmailTaken(String email) {
        ParameterizedSparqlString queryString = new ParameterizedSparqlString();
        queryString.setCommandText(queryRepository.getQuery(Queries.isEmailTaken));
        queryString.setLiteral("email", email);

        Query query = QueryFactory.create(queryString.asQuery());
        QueryExecution queryExecution = QueryExecutionFactory.sparqlService(JenaPreferences.SPARQLEndpoint, query);
        return queryExecution.execAsk();
    }

    @Override
    public Person getPersonByLoginEmail(String email) {
        ParameterizedSparqlString queryString = new ParameterizedSparqlString();
        queryString.setCommandText(queryRepository.getQuery(Queries.getPersonByEmail));
        queryString.setLiteral("email", email, XSDDatatype.XSDstring);

        Query query = queryString.asQuery();
        QueryExecution queryExecution = QueryExecutionFactory.sparqlService(JenaPreferences.SPARQLEndpoint, query);
        Model model = queryExecution.execConstruct();
        Person person = PersonObjectMapper.generatePerson(model);
        return person;
    }

    @Override
    public Person getPersonById(String id) {
        ParameterizedSparqlString queryString = new ParameterizedSparqlString();
        queryString.setCommandText(queryRepository.getQuery(Queries.getPersonByEmail));
        queryString.setIri("person", CVR.getURI(id));

        Query query = queryString.asQuery();
        QueryExecution queryExecution = QueryExecutionFactory.sparqlService(JenaPreferences.SPARQLEndpoint, query);
        Model model = queryExecution.execConstruct();
        Person person = PersonObjectMapper.generatePerson(model);
        return person;
    }

    @Override
    public Person deletePerson(Person person) {
        return null;
    }
}
