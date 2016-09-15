package com.cver.team.persistence.jena.objectMappers.externalResourceObjectMappers;

import com.cver.team.model.externalresource.SkillResource;
import com.cver.team.persistence.jena.helper.IdentifierGenerator;
import com.cver.team.persistence.jena.namespaces.CVR;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by PC on 25/08/2016.
 */
@Component
public class SkillResourceObjectMapper {
    @Autowired
    ExternalResourceObjectMapper externalResourceObjectMapper;

    public SkillResource generateSkillResource(Model model, Resource resource) {
        SkillResource skillResource = new SkillResource();
        skillResource = externalResourceObjectMapper.generateExternalResource(model, resource, skillResource);
        return skillResource;
    }

    public void createModel(SkillResource resource, Model model) {
        if (resource.getIdentifier() != null)
            return;
        resource.setIdentifier(IdentifierGenerator.generateIdentifier());
        Resource res = CVR.getResource(resource.getIdentifier().getId());
        createModel(resource, model, res);
    }

    private <T extends Resource> void createModel(SkillResource resource, Model model, Resource res) {
        externalResourceObjectMapper.createModel(resource, model, res);
    }
}
