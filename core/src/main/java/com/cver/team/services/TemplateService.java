package com.cver.team.services;

import com.cver.team.model.entity.Template;

import java.util.List;
import java.util.Map;

/**
 * Created by Dimitar on 2/28/2016.
 */
public interface TemplateService {
     Map getTemplateByName(String name);

    Template getTemplate(String id);

    List<Template> queryTemplates(String query, String type, Integer offset, Integer limit, String userId);

    List<Template> queryCvTemplates(String query, String type, Integer offset, Integer limit, String userId);

    List<Template> queryCertificateTemplates(String query, String type, Integer offset, Integer limit, String userId);
}
