package com.cver.team.persistence.jena.objectMappers.externalResourceObjectMappers;

import com.cver.team.model.externalresource.StaticFile;
import com.cver.team.persistence.jena.helper.ByteConverter;
import com.cver.team.persistence.jena.namespaces.CVO;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by PC on 10/09/2016.
 */
@Component
public class StaticFileObjectMapper {
    @Autowired
    ExternalResourceObjectMapper externalResourceObjectMapper;

    public StaticFile generatePerson(Model model, String uri) {
        StaticFile staticFile = new StaticFile();
        Resource resource = model.getResource(uri);
        staticFile = externalResourceObjectMapper.generateExternalResource(model, resource, staticFile);
        Statement statement = resource.getProperty(CVO.getProperty("name"));
        if (statement != null)
            staticFile.setName(statement.getObject().asLiteral().getString());

        statement = resource.getProperty(CVO.getProperty("description"));
        if (statement != null)
            staticFile.setDescription(statement.getObject().asLiteral().getString());

        statement = resource.getProperty(CVO.getProperty("contentType"));
        staticFile.setContentType(statement.getObject().asLiteral().getString());

        statement = resource.getProperty(CVO.getProperty("url"));
        staticFile.setUrl(statement.getObject().asLiteral().getString());

        statement = resource.getProperty(CVO.getProperty("val"));
        staticFile.setValue(ByteConverter.convertStringToBytes(statement.getObject().asLiteral().getString()));

        return staticFile;
    }
}
