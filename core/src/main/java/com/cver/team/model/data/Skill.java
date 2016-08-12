package com.cver.team.model.data;

import com.cver.team.model.externalresource.SkillResource;



public class Skill extends Expertise {
    private SkillResource resource;

    public SkillResource getResource() {
        return resource;
    }

    public void setResource(SkillResource resource) {
        this.resource = resource;
    }
}
