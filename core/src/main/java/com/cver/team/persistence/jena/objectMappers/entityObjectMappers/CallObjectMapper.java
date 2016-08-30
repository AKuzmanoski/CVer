package com.cver.team.persistence.jena.objectMappers.entityObjectMappers;

import com.cver.team.model.entity.Call;
import com.cver.team.persistence.jena.namespaces.CVO;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;

/**
 * Created by PC on 17/08/2016.
 */
public class CallObjectMapper {
    public static Call generateCall(Model model, Resource resource) {
        Call call = new Call();

        call = EntityObjectMapper.generateEntity(model, resource, call);

        // Owner
        Statement statement = resource.getProperty(CVO.getProperty("owner"));
        if (statement != null)
            call.setOwner(OrganizationObjectMapper.generateOrganization(model, statement.getObject().asResource()));

        return call;
    }
}
