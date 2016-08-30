package com.cver.team.persistence.jena.objectMappers.externalResourceObjectMappers;

import com.cver.team.model.entity.Call;
import com.cver.team.model.externalresource.tag.CertificateTag;
import com.cver.team.model.externalresource.tag.ProjectTag;
import com.cver.team.model.externalresource.tag.Tag;
import com.cver.team.model.externalresource.tag.TemplateTag;
import com.cver.team.persistence.jena.namespaces.CVO;
import com.cver.team.persistence.jena.objectMappers.entityObjectMappers.EntityObjectMapper;
import com.cver.team.persistence.jena.objectMappers.entityObjectMappers.OrganizationObjectMapper;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.vocabulary.RDF;

/**
 * Created by PC on 25/08/2016.
 */
public class TagObjectMapper {
    public static Tag generateTag(Model model, Resource resource) {
        Tag tag;

        if (resource.hasProperty(RDF.type, CVO.getResource("CertificateTag")))
            tag = new CertificateTag();
        else if (resource.hasProperty(RDF.type, CVO.getResource("TemplateTag")))
            tag = new TemplateTag();
        else if (resource.hasProperty(RDF.type, CVO.getResource("ProjectTag")))
            tag = new ProjectTag();
        else tag = new Tag();

        tag = ExternalResourceObjectMapper.generateExternalResource(model, resource, tag);

        return tag;
    }
}
