package com.cver.team.persistence.jena.objectMappers.externalResourceObjectMappers;

import com.cver.team.model.externalresource.StaticFile;
import com.cver.team.persistence.jena.helper.ByteConverter;
import com.cver.team.persistence.jena.namespaces.CVO;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;

/**
 * Created by PC on 10/09/2016.
 */
public class StaticFileObjectMapper {
    public static StaticFile generatePerson(Model model, String uri) {
        StaticFile staticFile = new StaticFile();
        Resource resource = model.getResource(uri);
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
