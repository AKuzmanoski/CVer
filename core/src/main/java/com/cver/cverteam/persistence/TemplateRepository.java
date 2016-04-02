package com.cver.cverteam.persistence;

import com.cver.cverteam.model.Template;

/**
 * Created by Dimitar on 2/28/2016.
 */
public interface TemplateRepository {
    Template getTemplateByName(String name);
}
