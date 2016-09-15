package com.cver.team.persistence.jena.objectMappers.entityObjectMappers;

import com.cver.team.model.entity.organization.EducationalInstitution;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by PC on 25/08/2016.
 */
@Component
public class EducationalInstitutionObjectMapper {
    @Autowired
    OrganizationObjectMapper organizationObjectMapper;

    public EducationalInstitution generateEducationalInstitution(Model model, Resource resource) {
        EducationalInstitution educationalInstitution = new EducationalInstitution();
        educationalInstitution = organizationObjectMapper.generateOrganization(model, resource, educationalInstitution);
        return educationalInstitution;
    }
}
