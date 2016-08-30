package com.cver.team.persistence.jena.objectMappers.dataObjectMappers;

import com.cver.team.model.data.Image;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;

/**
 * Created by PC on 25/08/2016.
 */
public class ImageObjectMapper {
    public static Image generateImage(Model model, Resource resource) {
        Image image = new Image();

        image = MediaObjectMapper.generateMedia(model, resource, image);

        return image;
    }
}
