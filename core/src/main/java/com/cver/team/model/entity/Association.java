package com.cver.team.model.entity;

import com.cver.team.model.data.*;
import com.cver.team.model.entity.organization.EducationalInstitution;
import com.cver.team.model.entity.organization.Firm;
import com.cver.team.model.entity.organization.Institution;
import com.fasterxml.jackson.annotation.JsonSubTypes;

@JsonSubTypes({
        @JsonSubTypes.Type(value = Project.class),
        @JsonSubTypes.Type(value = Organization.class),
        @JsonSubTypes.Type(value = EducationalInstitution.class),
        @JsonSubTypes.Type(value = Firm.class),
        @JsonSubTypes.Type(value = Institution.class),
})
public interface Association extends Entity {
}
