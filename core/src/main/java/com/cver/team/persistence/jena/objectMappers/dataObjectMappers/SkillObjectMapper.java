package com.cver.team.persistence.jena.objectMappers.dataObjectMappers;

import com.cver.team.model.data.Skill;
import com.cver.team.model.externalresource.ExternalResource;
import com.cver.team.persistence.jena.namespaces.CVO;
import com.cver.team.persistence.jena.objectMappers.externalResourceObjectMappers.ExternalResourceObjectMapper;
import com.cver.team.persistence.jena.objectMappers.externalResourceObjectMappers.SkillResourceObjectMapper;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;

/**
 * Created by PC on 25/08/2016.
 */
public class SkillObjectMapper {
    public static Skill generateSkill(Model model, Resource resource) {
        Skill skill = new Skill();
        skill = DataObjectMapper.generateData(model, resource, skill);

        // Resource
        Statement statement = resource.getProperty(CVO.getProperty("resource"));
        if (statement != null)
            skill.setResource(SkillResourceObjectMapper.generateSkillResource(model, statement.getObject().asResource()));

        return skill;
    }
}
