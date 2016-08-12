package com.cver.team.persistence.jena;

import com.cver.team.model.entity.Person;
import com.cver.team.model.literal.Identifier;
import com.cver.team.persistence.PersonRepository;
import com.cver.team.persistence.helper.MailEncoder;
import com.cver.team.persistence.helper.URIMaker;
import com.cver.team.persistence.jena.helper.JenaPreferences;
import com.cver.team.persistence.jena.namespaces.CVR;
import com.cver.team.persistence.jena.objectMappers.PersonObjectMapper;
import com.cver.team.persistence.jena.queries.Queries;
import org.apache.jena.datatypes.xsd.XSDDatatype;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.update.UpdateExecutionFactory;
import org.apache.jena.update.UpdateProcessor;
import org.apache.jena.update.UpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class PersonRepositoryJena implements PersonRepository {
    @Autowired
    QueryRepository queryRepository;
    @Autowired
    MailEncoder mailEncoder;

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
        if (person.getProfilePictureUrl() != null) {
            queryString.setIri("profilePicture", CVR.generateURI());
            queryString.setLiteral("profilePictureUrl", person.getProfilePictureUrl(),  XSDDatatype.XSDstring);
        }
        if (person.getCoverPictureUrl() != null) {
            queryString.setIri("cover", CVR.generateURI());
            queryString.setLiteral("coverUrl", person.getCoverPictureUrl(),  XSDDatatype.XSDstring);
        }

        // Set account values
        queryString.setLiteral("provider", person.getProvider().name(), XSDDatatype.XSDstring);
        queryString.setLiteral("role", person.getRole().name(), XSDDatatype.XSDstring);
        if (person.getPassword() != null)
            queryString.setLiteral("password", person.getPassword(), XSDDatatype.XSDstring);
        queryString.setIri("loginEmail", CVR.generateURI());
        queryString.setLiteral("email", person.getEmail(), XSDDatatype.XSDstring);
        queryString.setLiteral("hashedEmail", mailEncoder.encode(person.getEmail()), XSDDatatype.XSDstring);

        // Set person values
        queryString.setLiteral("firstName", person.getFirstName());
        queryString.setLiteral("lastName", person.getLastName());
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
