package com.cver.team.persistence.jena.objectMappers.entityObjectMappers;

import com.cver.team.model.entity.Agent;
import com.cver.team.model.entity.Organization;
import com.cver.team.model.entity.Person;
import com.cver.team.persistence.helper.URIMaker;
import com.cver.team.persistence.jena.helper.DateTimeConverter;
import com.cver.team.persistence.jena.helper.IdentifierGenerator;
import com.cver.team.persistence.jena.namespaces.CVO;
import com.cver.team.persistence.jena.namespaces.CVR;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.impl.StatementImpl;
import org.apache.jena.vocabulary.RDF;

import java.util.Calendar;

/**
 * Created by PC on 17/08/2016.
 */
public class AgentObjectMapper {
    public static <T extends Agent> T generateAgent(Model model, Resource resource, T agent) {
        agent = EntityObjectMapper.generateEntity(model, resource, agent);

        // Profile Picture
        Statement statement = resource.getProperty(CVO.getProperty("profilePicture"));
        if (statement != null) {
            Resource profilePicture = statement.getObject().asResource();
            statement = profilePicture.getProperty(CVO.getProperty("url"));
            agent.setProfilePictureUrl(statement.getObject().asLiteral().getString());
        }
        return agent;
    }

    public static void createModel(Agent agent, Model model) {
        if (agent.getIdentifier() != null)
            return;

        // Check type
        if (agent instanceof Person) {
            PersonObjectMapper.createModel((Person)agent, model);
            return;
        }
        if (agent instanceof Organization) {
            OrganizationObjectMapper.createModel((Organization)agent, model);
            return;
        }

        agent.setIdentifier(IdentifierGenerator.generateIdentifier());
        Resource resource = CVR.getResource(agent.getIdentifier().getId());
        createModel(agent, model, resource);
    }

    public static <T extends Agent> void createModel(T agent, Model model, Resource resource) {
        EntityObjectMapper.createModel(agent, model, resource);

        model.add(new StatementImpl(resource, RDF.type, CVO.getResource("Agent")));

        // Create current date
        java.util.Date date = new java.util.Date();
        Calendar calendar = DateTimeConverter.getCalendar(date);
        Literal dateTime = model.createTypedLiteral(calendar);


        // Set profile picture
        String profilePictureId = URIMaker.generateUri();
        Resource profilePicture = CVR.getResource(profilePictureId);
        Literal profilePictureURI = model.createTypedLiteral(agent.getProfilePictureUrl());
        model.add(new StatementImpl(resource, CVO.getProperty("cover"), profilePicture));
        model.add(new StatementImpl(profilePicture, CVO.getProperty("url"), profilePictureURI));
        model.add(new StatementImpl(profilePicture, CVO.getProperty("creationDate"), dateTime));
        model.add(new StatementImpl(profilePicture, CVO.getProperty("lastModified"), dateTime));
    }
}
