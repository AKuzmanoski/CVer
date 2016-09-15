package com.cver.team.persistence.jena.objectMappers.externalResourceObjectMappers;

import com.cver.team.model.externalresource.tag.CertificateTag;
import com.cver.team.model.externalresource.tag.ProjectTag;
import com.cver.team.model.externalresource.tag.Tag;
import com.cver.team.model.externalresource.tag.TemplateTag;
import com.cver.team.persistence.jena.namespaces.CVO;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.vocabulary.RDF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by PC on 25/08/2016.
 */
@Component
public class TagObjectMapper {
    @Autowired
    ExternalResourceObjectMapper externalResourceObjectMapper;

    public Tag generateTag(Model model, Resource resource) {
        Tag tag;

        if (resource.hasProperty(RDF.type, CVO.getResource("CertificateTag")))
            tag = new CertificateTag();
        else if (resource.hasProperty(RDF.type, CVO.getResource("TemplateTag")))
            tag = new TemplateTag();
        else if (resource.hasProperty(RDF.type, CVO.getResource("ProjectTag")))
            tag = new ProjectTag();
        else tag = new Tag();

        tag = externalResourceObjectMapper.generateExternalResource(model, resource, tag);

        return tag;
    }
}
