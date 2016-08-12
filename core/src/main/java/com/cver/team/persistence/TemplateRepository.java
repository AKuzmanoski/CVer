package com.cver.team.persistence;

import com.cver.team.model.entity.Template;

/**
 * Created by Dimitar on 2/28/2016.
 */
public interface TemplateRepository {
    Template getTemplateByName(String name);
}
