package com.cver.team.model.data;

import com.cver.team.model.data.string.ValueProposition;
import com.cver.team.model.entity.Document;
import com.cver.team.model.entity.Template;

/**
 * Created by PC on 25/08/2016.
 */
public class Certificate extends CertificateCard implements Document {
    private Template template;
    private ValueProposition valueProposition;
    private Expertise forExpertise;

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

    public Expertise getForExpertise() {
        return forExpertise;
    }

    public void setForExpertise(Expertise forExpertise) {
        this.forExpertise = forExpertise;
    }
}
