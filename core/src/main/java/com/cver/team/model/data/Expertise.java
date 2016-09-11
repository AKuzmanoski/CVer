package com.cver.team.model.data;

import com.fasterxml.jackson.annotation.JsonSubTypes;

/**
 * Created by PC on 13/08/2016.
 */
@JsonSubTypes({
        @JsonSubTypes.Type(value = Experience.class),
        @JsonSubTypes.Type(value = WorkExperience.class),
        @JsonSubTypes.Type(value = Skill.class),
        @JsonSubTypes.Type(value = ProjectExperience.class),
        @JsonSubTypes.Type(value = EducationalExperience.class)
})
public class Expertise extends Data {
}
