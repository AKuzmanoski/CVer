package com.cver.team.model.entity;

import com.cver.team.model.Field;
import com.cver.team.model.data.Experience;
import com.cver.team.model.tag.ProjectTag;
import com.github.andrewoma.dexx.collection.List;

/**
 * Created by Dimitar on 7/7/2016.
 */
    public class Project extends Entity {

    private String externalLink;

    private Field field;

    private ProjectTag projectTag;

    public ProjectTag getProjectTag() {
        return projectTag;
    }

    public void setProjectTag(ProjectTag projectTag) {
        this.projectTag = projectTag;
    }

    public List<Person> getMembers() {
        return members;
    }

    public void setMembers(List<Person> members) {
        this.members = members;
    }

    private List<Person> members;

    public String getExternalLink() {
        return externalLink;
    }

    public void setExternalLink(String externalLink) {
        this.externalLink = externalLink;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

}
