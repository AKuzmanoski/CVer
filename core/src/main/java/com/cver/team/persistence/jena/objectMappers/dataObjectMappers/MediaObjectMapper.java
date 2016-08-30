package com.cver.team.persistence.jena.objectMappers.dataObjectMappers;

import com.cver.team.model.data.Media;
import com.cver.team.persistence.jena.namespaces.CVO;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import sun.awt.ModalityListener;

/**
 * Created by PC on 25/08/2016.
 */
public class MediaObjectMapper {
    public static <T extends Media> T generateMedia(Model model, Resource resource, T media) {
        media = DataObjectMapper.generateData(model, resource, media);

        // Url
        Statement statement = resource.getProperty(CVO.getProperty("url"));
        if (statement != null)
            media.setUrl(statement.getObject().asLiteral().getString());

        return media;
    }
}
