/**
 * Created by Dimitar on 2/28/2016.
 */

package com.cver.team.services.impl;

import com.cver.team.model.entity.Template;
import com.cver.team.persistence.TemplateRepository;
import com.cver.team.services.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
import java.util.List;
import java.util.Map;


@Service
public class TemplateServiceImpl implements TemplateService {

    @Autowired
    TemplateRepository templateRepository;


    public Map getTemplateByName(String name) {
//        Template template = templateRepository.getTemplateByName(name);
//        System.err.println("The template name is : "+ template.getName());
//        File defaultTemplate = new File( servletContext.getRealPath(template.getLocation()));
//        try {
//            BufferedReader br = new BufferedReader( new FileReader(defaultTemplate));
//            StringBuilder sb = new StringBuilder();
//            String line;
//            while((line = br.readLine()) != null)
//            {
//
//                sb.append(line);
//            }
//
//            return Collections.singletonMap("response",sb.toString());
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


        return null;
    }

    @Override
    public Template getTemplate(String id) {
        return templateRepository.getTemplate(id);
    }

    @Override
    public List<Template> queryTemplates(String query, String type, Integer offset, Integer limit, String userId) {
        return templateRepository.queryTemplates(query, type, offset, limit, userId);
    }

    @Override
    public List<Template> queryCvTemplates(String query, String type, Integer offset, Integer limit, String userId) {
        return templateRepository.queryCvTemplates(query, type, offset, limit, userId);
    }

    @Override
    public List<Template> queryCertificateTemplates(String query, String type, Integer offset, Integer limit, String userId) {
        return templateRepository.queryCertificateTemplates(query, type, offset, limit, userId);
    }

    @Override
    public List<String> autocomplete(String query, Integer limit) {
        return templateRepository.autocomplete(query, limit);
    }

    @Override
    public List<String> types(String query, Integer limit) {
        return templateRepository.types(query, limit);
    }
}

