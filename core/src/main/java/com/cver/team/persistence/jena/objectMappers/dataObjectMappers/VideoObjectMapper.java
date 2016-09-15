package com.cver.team.persistence.jena.objectMappers.dataObjectMappers;

import com.cver.team.model.data.Image;
import com.cver.team.model.data.Video;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by PC on 25/08/2016.
 */
@Component
public class VideoObjectMapper {
    @Autowired
    MediaObjectMapper mediaObjectMapper;

    public Video generateVideo(Model model, Resource resource) {
        Video video = new Video();

        video = mediaObjectMapper.generateMedia(model, resource, video);

        return video;
    }
}
