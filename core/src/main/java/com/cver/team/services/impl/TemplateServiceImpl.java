/**
 * Created by Dimitar on 2/28/2016.
 */

package com.cver.team.services.impl;

import com.cver.team.model.entity.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cver.team.persistence.TemplateRepository;
import com.cver.team.services.TemplateService;

import javax.servlet.ServletContext;
import java.io.*;
import java.util.Collections;
import java.util.Map;


@Service
public class TemplateServiceImpl implements TemplateService {

    @Autowired
    ServletContext servletContext;

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
    }

