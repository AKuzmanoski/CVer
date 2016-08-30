package com.cver.team.model.entity;

import com.cver.team.model.data.string.ValueProposition;

/**
 * Created by PC on 25/08/2016.
 */
public interface Document extends DocumentCard {
    Template getTemplate();

    void setTemplate(Template template);

    ValueProposition getValueProposition();

    void setValueProposition(ValueProposition valueProposition);
}
