package com.cver.team.persistence.jena.objectMappers.entityObjectMappers;

import com.cver.team.model.entity.organization.EducationalInstitution;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;

/**
 * Created by PC on 25/08/2016.
 */
public class EducationalInstitutionObjectMapper {
    public static EducationalInstitution generateEducationalInstitution(Model model, Resource resource) {
        EducationalInstitution educationalInstitution = new EducationalInstitution();
        educationalInstitution = OrganizationObjectMapper.generateOrganization(model, resource, educationalInstitution);
        return educationalInstitution;
    }
}
