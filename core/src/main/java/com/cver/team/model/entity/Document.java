package com.cver.team.model.entity;

import com.cver.team.model.data.*;
import com.cver.team.model.data.string.ValueProposition;

import java.util.List;

/**
 * Created by Dimitar on 8/12/2016.
 */
public interface Document extends Entity {
    Template getTemplate();

    void setTemplate(Template template);

    String getTitle();

    void setTitle(String title);

    ValueProposition getValueProposition();

    void setValueProposition(ValueProposition valueProposition);
}
