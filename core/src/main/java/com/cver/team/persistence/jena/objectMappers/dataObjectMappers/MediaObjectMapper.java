package com.cver.team.persistence.jena.objectMappers.dataObjectMappers;

import com.cver.team.model.data.Media;
import com.cver.team.persistence.jena.namespaces.CVO;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sun.awt.ModalityListener;

/**
 * Created by PC on 25/08/2016.
 */
@Component
public class MediaObjectMapper {
    @Autowired
    DataObjectMapper dataObjectMapper;

    public <T extends Media> T generateMedia(Model model, Resource resource, T media) {
        media = dataObjectMapper.generateData(model, resource, media);

        // Url
        Statement statement = resource.getProperty(CVO.getProperty("url"));
        if (statement != null)
            media.setUrl(statement.getObject().asLiteral().getString());

        return media;
    }
}
