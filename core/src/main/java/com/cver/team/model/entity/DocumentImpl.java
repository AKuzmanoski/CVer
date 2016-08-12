package com.cver.team.model.entity;

import com.cver.team.model.data.Experience;
import com.cver.team.model.data.Skill;
import com.cver.team.model.data.string.ValueProposition;

import java.util.List;

/**
 * Created by PC on 12/08/2016.
 */
public class DocumentImpl extends EntityImpl implements Document {
    private Template template;
    private String title;
    private ValueProposition valueProposition;

    public Template getTemplate() {
        return template;
    }

    public void setTemplate(Template template) {
        this.template = template;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public ValueProposition getValueProposition() {
        return valueProposition;
    }

    public void setValueProposition(ValueProposition valueProposition) {
        this.valueProposition = valueProposition;
    }
}
