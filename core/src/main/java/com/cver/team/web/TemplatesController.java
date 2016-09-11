package com.cver.team.web;

import com.cver.team.model.entity.Person;
import com.cver.team.model.entity.Template;
import com.cver.team.services.TemplateService;
import com.cver.team.web.ResponseExceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/template")
public class TemplatesController {
    @Autowired
    TemplateService templateService;

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Template getTemplate(@PathVariable  String id) {
        Template template = templateService.getTemplate(id);
        if (template == null)
            throw new ResourceNotFoundException();
        return template;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Template> getTemplates(@RequestParam String query, String type, Integer offset, Integer limit, HttpSession session) {
        Person person = (Person)session.getAttribute("user");

        if (person == null)
            return templateService.queryTemplates(query, type, offset, limit, null);
        return templateService.queryTemplates(query, type, offset, limit, person.getIdentifier().getId());
    }

    @RequestMapping(value = "/cv", method = RequestMethod.GET)
    public List<Template> getCvTemplates(@RequestParam String query, String type, Integer offset, Integer limit, HttpSession session) {
        Person person = (Person)session.getAttribute("user");

        if (person == null)
            return templateService.queryCvTemplates(query, type, offset, limit, null);
        return templateService.queryCvTemplates(query, type, offset, limit, person.getIdentifier().getId());
    }

    @RequestMapping(value = "/certificate", method = RequestMethod.GET)
    public List<Template> getCertificateTemplates(@RequestParam String query, String type, Integer offset, Integer limit, HttpSession session) {
        Person person = (Person)session.getAttribute("user");

        if (person == null)
            return templateService.queryCertificateTemplates(query, type, offset, limit, null);
        return templateService.queryCertificateTemplates(query, type, offset, limit, person.getIdentifier().getId());
    }

    @RequestMapping(value = "/autocomplete", method = RequestMethod.GET)
    public List<String> autocomplete(@RequestParam String query, @RequestParam Integer limit, HttpSession session) {
        Person person = (Person)session.getAttribute("user");
        return templateService.autocomplete(query, person != null ? person.getIdentifier().getId() : null, limit);
    }

    @RequestMapping(value = "/types", method = RequestMethod.GET)
    public List<String> types(@RequestParam String query, @RequestParam Integer limit, HttpSession session) {
        Person person = (Person)session.getAttribute("user");
        return templateService.types(query, person != null ? person.getIdentifier().getId() : null, limit);
    }

    @RequestMapping(value = "/cv/autocomplete", method = RequestMethod.GET)
    public List<String> autocompleteCV(@RequestParam String query, @RequestParam Integer limit, HttpSession session) {
        Person person = (Person)session.getAttribute("user");
        return templateService.autocompleteCV(query, person != null ? person.getIdentifier().getId() : null, limit);
    }

    @RequestMapping(value = "/cv/types", method = RequestMethod.GET)
    public List<String> typesCV(@RequestParam String query, @RequestParam Integer limit, HttpSession session) {
        Person person = (Person)session.getAttribute("user");
        return templateService.typesCV(query, person != null ? person.getIdentifier().getId() : null, limit);
    }

    @RequestMapping(value = "/certificate/autocomplete", method = RequestMethod.GET)
    public List<String> autocompleteCertificate(@RequestParam String query, @RequestParam Integer limit, HttpSession session) {
        Person person = (Person)session.getAttribute("user");
        return templateService.autocompleteCertificate(query, person != null ? person.getIdentifier().getId() : null, limit);
    }

    @RequestMapping(value = "/certificate/types", method = RequestMethod.GET)
    public List<String> typesCertificate(@RequestParam String query, @RequestParam Integer limit, HttpSession session) {
        Person person = (Person)session.getAttribute("user");
        return templateService.typesCertificate(query, person != null ? person.getIdentifier().getId() : null, limit);
    }
}
