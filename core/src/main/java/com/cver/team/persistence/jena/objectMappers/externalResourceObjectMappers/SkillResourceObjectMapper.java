package com.cver.team.persistence.jena.objectMappers.externalResourceObjectMappers;

import com.cver.team.model.externalresource.SkillResource;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;

/**
 * Created by PC on 25/08/2016.
 */
public class SkillResourceObjectMapper {
    public static SkillResource generateSkillResource(Model model, Resource resource) {
        SkillResource skillResource = new SkillResource();
        skillResource = ExternalResourceObjectMapper.generateExternalResource(model, resource, skillResource);
        return skillResource;
    }
}
