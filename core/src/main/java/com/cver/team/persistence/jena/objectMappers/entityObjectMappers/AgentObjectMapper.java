package com.cver.team.persistence.jena.objectMappers.entityObjectMappers;

import com.cver.team.model.entity.Agent;
import com.cver.team.persistence.jena.namespaces.CVO;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;

/**
 * Created by PC on 17/08/2016.
 */
public class AgentObjectMapper {
    public static <T extends Agent> T generateAgent(Model model, Resource resource, T agent) {
        agent = EntityObjectMapper.generateEntity(model, resource, agent);

        // Profile Picture
        Statement statement = resource.getProperty(CVO.getProperty("profilePicture"));
        if (statement != null) {
            Resource profilePicture =  statement.getObject().asResource();
            statement = profilePicture.getProperty(CVO.getProperty("url"));
            agent.setProfilePictureUrl(statement.getObject().asLiteral().getString());
        }
        return agent;
    }
}
