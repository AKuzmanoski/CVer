package com.cver.team.model.entity;

import com.cver.team.model.data.string.ValueProposition;
import com.cver.team.model.externalresource.tag.Tag;
import com.cver.team.model.literal.Identifier;

import java.util.Date;
import java.util.List;

/**
 * Created by PC on 25/08/2016.
 */
public class DocumentImpl extends DocumentCardImpl implements Document {
    private Template template;
    private ValueProposition valueProposition;


    @Override
    public Template getTemplate() {
        return template;
    }

    @Override
    public void setTemplate(Template template) {
        this.template = template;
    }

    @Override
    public ValueProposition getValueProposition() {
        return valueProposition;
    }

    @Override
    public void setValueProposition(ValueProposition valueProposition) {
        this.valueProposition = valueProposition;
    }
}
