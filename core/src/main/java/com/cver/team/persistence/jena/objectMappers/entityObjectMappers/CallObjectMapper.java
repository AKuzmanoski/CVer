package com.cver.team.persistence.jena.objectMappers.entityObjectMappers;

import com.cver.team.model.entity.Call;
import com.cver.team.model.entity.Entity;
import com.cver.team.persistence.jena.namespaces.CVO;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by PC on 17/08/2016.
 */
@Component
public class CallObjectMapper {
    @Autowired
    EntityObjectMapper entityObjectMapper;
    @Autowired
    OrganizationObjectMapper organizationObjectMapper;

    public Call generateCall(Model model, Resource resource) {
        Call call = new Call();

        call = entityObjectMapper.generateEntity(model, resource, call);

        // Owner
        Statement statement = resource.getProperty(CVO.getProperty("owner"));
        if (statement != null)
            call.setOwner(organizationObjectMapper.generateOrganization(model, statement.getObject().asResource()));

        return call;
    }

    public Call generateCall(Model model, String id) {
        Resource resource = model.getResource(id);
        return generateCall(model, resource);
    }
}
