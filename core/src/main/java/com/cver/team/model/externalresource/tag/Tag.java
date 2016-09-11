package com.cver.team.model.externalresource.tag;

import com.cver.team.model.entity.Organization;
import com.cver.team.model.entity.Project;
import com.cver.team.model.entity.organization.EducationalInstitution;
import com.cver.team.model.entity.organization.Firm;
import com.cver.team.model.entity.organization.Institution;
import com.cver.team.model.externalresource.ExternalResource;
import com.fasterxml.jackson.annotation.JsonSubTypes;

/**
 * Created by Dimitar on 8/12/2016.
 */
@JsonSubTypes({
        @JsonSubTypes.Type(value = ProjectTag.class),
        @JsonSubTypes.Type(value = CertificateTag.class),
        @JsonSubTypes.Type(value = TemplateTag.class)
})
public class Tag extends ExternalResource {
}
