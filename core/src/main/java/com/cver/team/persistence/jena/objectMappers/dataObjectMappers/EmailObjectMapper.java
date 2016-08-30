package com.cver.team.persistence.jena.objectMappers.dataObjectMappers;

import com.cver.team.model.data.Email;
import com.cver.team.persistence.jena.namespaces.CVO;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;

/**
 * Created by PC on 25/08/2016.
 */
public class EmailObjectMapper {
    public static Email generateEmail(Model model, Resource resource) {
        Email email = new Email();
        email = DataObjectMapper.generateData(model, resource, email);

        // mbox
        Statement statement = resource.getProperty(CVO.getProperty("mbox"));
        if (statement != null) {
            email.setMbox(statement.getObject().asLiteral().getString());
        }

        // hashedMbox
        statement = resource.getProperty(CVO.getProperty("hashedMbox"));
        if (statement != null) {
            email.setHashedMbox(statement.getObject().asLiteral().getString());
        }

        return email;
    }
}
