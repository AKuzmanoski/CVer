package com.cver.team.persistence.jena.objectMappers.externalResourceObjectMappers;

import com.cver.team.model.data.Skill;
import com.cver.team.model.externalresource.Degree;
import com.cver.team.persistence.jena.helper.IdentifierGenerator;
import com.cver.team.persistence.jena.namespaces.CVO;
import com.cver.team.persistence.jena.namespaces.CVR;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by PC on 25/08/2016.
 */
@Component
public class DegreeObjectMapper {
    @Autowired
    ExternalResourceObjectMapper externalResourceObjectMapper;

    public Degree generateDegree(Model model, Resource resource) {
        Degree degree = new Degree();
        degree = externalResourceObjectMapper.generateExternalResource(model, resource, degree);
        return degree;
    }

    public void createModel(Degree degree, Model model) {
        if (degree.getIdentifier() != null)
            return;
        degree.setIdentifier(IdentifierGenerator.generateIdentifier());
        Resource resource = CVR.getResource(degree.getIdentifier().getId());
        createModel(degree, model, resource);
    }

    public <T extends Degree> void createModel(T degree, Model model, Resource resource) {
        externalResourceObjectMapper.createModel(degree, model, resource);
    }
}
