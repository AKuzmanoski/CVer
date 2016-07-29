package com.cver.team.persistence.jena.objectMappers;

import com.cver.team.model.Person;
import com.cver.team.model.Provider;
import com.cver.team.model.Role;
import com.cver.team.model.literal.Identifier;
import com.cver.team.persistence.jena.namespaces.CVO;
import com.cver.team.persistence.jena.namespaces.CVR;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ResIterator;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.vocabulary.RDF;

/**
 * Created by User on 7/27/2016.
 */
public class PersonObjectMapper {
    public static Person generatePerson(Model model) {
        ResIterator iterator = model.listResourcesWithProperty(RDF.type, CVO.getResource("Person"));
        if (iterator.hasNext()) {
            Resource resource = iterator.nextResource();
            return generatePerson(model, resource);
        } else
            return null;
    }

    public static Person generatePerson(Model model, Resource resource) {
        Person person = new Person();

        // First Name
        Statement statement = resource.getProperty(CVO.getProperty("firstName"));
        person.setFirstName(statement.getObject().asLiteral().getString());

        // Last Name
        statement = resource.getProperty(CVO.getProperty("lastName"));
        person.setLastName(statement.getObject().asLiteral().getString());

        // Provider
        statement = resource.getProperty(CVO.getProperty("provider"));
        person.setProvider(Provider.valueOf(statement.getObject().asLiteral().getString()));

        // Role
        statement = resource.getProperty(CVO.getProperty("role"));
        person.setRole(Role.valueOf(statement.getObject().asLiteral().getString()));

        // Email
        statement = resource.getProperty(CVO.getProperty("email"));
        person.setEmail(statement.getObject().asLiteral().getString());

        // Password
        statement = resource.getProperty(CVO.getProperty("password"));
        if (statement != null)
            person.setPassword(statement.getObject().asLiteral().getString());

        // Name
        statement = resource.getProperty(CVO.getProperty("name"));
        if (statement != null)
            person.setName(statement.getObject().asLiteral().getString());

        // description
        statement = resource.getProperty(CVO.getProperty("description"));
        if (statement != null)
            person.setDescription(statement.getObject().asLiteral().getString());

        // Profile Picture
        statement = resource.getProperty(CVO.getProperty("profilePicture"));
        if (statement != null)
            person.setProfilePictureURL(statement.getObject().asLiteral().getString());

        // Profile Picture
        statement = resource.getProperty(CVO.getProperty("cover"));
        if (statement != null)
            person.setCoverUrl(statement.getObject().asLiteral().getString());

        person.setType("Person");

        // URI
        String uri = resource.getURI();
        Identifier identifier = new Identifier();
        identifier.setURI(uri);
        identifier.setId(CVR.getId(uri));
        person.setIdentifier(identifier);

        return person;
    }
}
