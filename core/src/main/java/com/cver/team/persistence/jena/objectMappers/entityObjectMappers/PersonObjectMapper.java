package com.cver.team.persistence.jena.objectMappers.entityObjectMappers;

import com.cver.team.model.Provider;
import com.cver.team.model.Role;
import com.cver.team.model.data.string.FirstName;
import com.cver.team.model.data.string.LastName;
import com.cver.team.model.entity.Person;
import com.cver.team.persistence.jena.helper.IdentifierGenerator;
import com.cver.team.persistence.jena.namespaces.CVO;
import com.cver.team.persistence.jena.namespaces.CVR;
import com.cver.team.persistence.jena.objectMappers.dataObjectMappers.stringObjectMappers.FirstNameObjectMapper;
import com.cver.team.persistence.jena.objectMappers.dataObjectMappers.stringObjectMappers.LastNameObjectMapper;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ResIterator;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.impl.StatementImpl;
import org.apache.jena.vocabulary.RDF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by User on 7/27/2016.
 */
@Component
public class PersonObjectMapper {
    @Autowired
    FirstNameObjectMapper firstNameObjectMapper;
    @Autowired
    LastNameObjectMapper lastNameObjectMapper;
    @Autowired
    AgentObjectMapper agentObjectMapper;

    public Person generatePerson(Model model) {
        ResIterator iterator = model.listResourcesWithProperty(RDF.type, CVO.getResource("Person"));
        if (iterator.hasNext()) {
            Resource resource = iterator.nextResource();
            return generatePerson(model, resource);
        } else
            return null;
    }

    public Person generatePerson(Model model, Resource resource) {
        Person person = new Person();

        person = agentObjectMapper.generateAgent(model, resource, person);

        // First Name
        Statement statement = resource.getProperty(CVO.getProperty("defaultFirstName"));
        if (statement != null) {
            Resource firstName = statement.getObject().asResource();
            statement = firstName.getProperty(CVO.getProperty("val"));
            person.setFirstName(statement.getObject().asLiteral().getString());
        }


        // Last Name
        statement = resource.getProperty(CVO.getProperty("defaultLastName"));
        if (statement != null) {
            Resource lastName = statement.getObject().asResource();
            statement = lastName.getProperty(CVO.getProperty("val"));
            person.setLastName(statement.getObject().asLiteral().getString());
        }

        // Account
        statement = resource.getProperty(CVO.getProperty("account"));
        if (statement != null) {
            Resource account = statement.getObject().asResource();

            statement = account.getProperty(CVO.getProperty("provider"));
            if (statement != null) {
                person.setProvider(Provider.valueOf(statement.getObject().asLiteral().getString()));

                statement = account.getProperty(CVO.getProperty("role"));
                person.setRole(Role.valueOf(statement.getObject().asLiteral().getString()));

                statement = account.getProperty(CVO.getProperty("password"));
                person.setPassword(statement.getObject().asLiteral().getString());

                statement = account.getProperty(CVO.getProperty("loginEmail"));
                Resource email = statement.getObject().asResource();
                statement = email.getProperty(CVO.getProperty("mbox"));
                person.setEmail(statement.getObject().asLiteral().getString());
            }
        }
        return person;
    }

    public Model generateModel(Person person, Model model) {
        if (person.getIdentifier() == null) {
            person.setIdentifier(IdentifierGenerator.generateIdentifier());

        }
        return model;
    }

    public void createModel(Person person, Model model) {
        if (person.getIdentifier() != null)
            return;

        person.setIdentifier(IdentifierGenerator.generateIdentifier());
        Resource resource = CVR.getResource(person.getIdentifier().getId());
        createModel(person, model, resource);
    }

    public <T extends Person> void createModel(T person, Model model, Resource resource) {
        agentObjectMapper.createModel(person, model, resource);

        FirstName firstName = new FirstName();
        firstName.setValue(person.getName());
        firstNameObjectMapper.createModel(firstName, model);
        model.add(new StatementImpl(resource, CVO.getProperty("defaultFirstName"), CVR.getResource(firstName.getIdentifier().getId())));

        LastName lastName = new LastName();
        lastName.setValue(person.getLastName());
        lastNameObjectMapper.createModel(lastName, model);
        model.add(new StatementImpl(resource, CVO.getProperty("defaultLastName"), CVR.getResource(lastName.getIdentifier().getId())));
    }
}
