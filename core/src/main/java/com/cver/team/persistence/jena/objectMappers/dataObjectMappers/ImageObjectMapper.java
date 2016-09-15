package com.cver.team.persistence.jena.objectMappers.dataObjectMappers;

import com.cver.team.model.data.Image;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by PC on 25/08/2016.
 */
@Component
public class ImageObjectMapper {
    @Autowired
    MediaObjectMapper mediaObjectMapper;

    public Image generateImage(Model model, Resource resource) {
        Image image = new Image();

        image = mediaObjectMapper.generateMedia(model, resource, image);

        return image;
    }
}
