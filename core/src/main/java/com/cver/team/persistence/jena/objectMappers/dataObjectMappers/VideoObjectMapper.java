package com.cver.team.persistence.jena.objectMappers.dataObjectMappers;

import com.cver.team.model.data.Image;
import com.cver.team.model.data.Video;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;

/**
 * Created by PC on 25/08/2016.
 */
public class VideoObjectMapper {
    public static Video generateVideo(Model model, Resource resource) {
        Video video = new Video();

        video = MediaObjectMapper.generateMedia(model, resource, video);

        return video;
    }
}
