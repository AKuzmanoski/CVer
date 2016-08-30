package com.cver.team.persistence;

import com.cver.team.model.entity.Template;

import java.util.List;

/**
 * Created by Dimitar on 2/28/2016.
 */
public interface TemplateRepository {
    Template getTemplateByName(String name);

    Template getTemplate(String id);

    List<Template> queryTemplates(String query, String type, Integer offset, Integer limit, String userId);

    List<Template> queryCvTemplates(String query, String type, Integer offset, Integer limit, String userId);

    List<Template> queryCertificateTemplates(String query, String type, Integer offset, Integer limit, String userId);
}
