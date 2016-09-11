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

    List<String> autocomplete(String query, String userId, Integer limit);

    List<String> types(String query, String userId, Integer limit);

    List<String> autocompleteCV(String query, String userId, Integer limit);

    List<String> typesCV(String query, String userId, Integer limit);

    List<String> autocompleteCertificate(String query, String userId, Integer limit);

    List<String> typesCertificate(String query, String userId, Integer limit);
}
