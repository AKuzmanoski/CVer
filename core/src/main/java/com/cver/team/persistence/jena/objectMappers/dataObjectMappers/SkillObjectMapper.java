package com.cver.team.persistence.jena.objectMappers.dataObjectMappers;

import com.cver.team.model.data.Skill;
import com.cver.team.model.externalresource.ExternalResource;
import com.cver.team.persistence.jena.helper.IdentifierGenerator;
import com.cver.team.persistence.jena.namespaces.CVO;
import com.cver.team.persistence.jena.namespaces.CVR;
import com.cver.team.persistence.jena.objectMappers.externalResourceObjectMappers.ExternalResourceObjectMapper;
import com.cver.team.persistence.jena.objectMappers.externalResourceObjectMappers.SkillResourceObjectMapper;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by PC on 25/08/2016.
 */
@Component
public class SkillObjectMapper {
    @Autowired
    DataObjectMapper dataObjectMapper;
    @Autowired
    SkillResourceObjectMapper skillResourceObjectMapper;

    public Skill generateSkill(Model model, Resource resource) {
        Skill skill = new Skill();
        skill = dataObjectMapper.generateData(model, resource, skill);

        // Resource
        Statement statement = resource.getProperty(CVO.getProperty("resource"));
        if (statement != null)
            skill.setResource(skillResourceObjectMapper.generateSkillResource(model, statement.getObject().asResource()));

        return skill;
    }

    public void createModel(Skill skill, Model model) {
        if (skill.getIdentifier() != null)
            return;
        skill.setIdentifier(IdentifierGenerator.generateIdentifier());
        Resource resource = CVR.getResource(skill.getIdentifier().getId());
        createModel(skill, model, resource);
    }

    private <T extends Skill> void createModel(T skill, Model model, Resource resource) {
        dataObjectMapper.createModel(skill, model, resource);

        if (skill.getResource() != null) {
            skillResourceObjectMapper.createModel(skill.getResource(), model);
            model.add(resource, CVO.getProperty("resource"), CVR.getResource(skill.getResource().getIdentifier().getId()));
        }
    }


}
