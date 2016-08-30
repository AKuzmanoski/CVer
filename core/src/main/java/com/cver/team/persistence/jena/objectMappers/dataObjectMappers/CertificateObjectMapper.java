package com.cver.team.persistence.jena.objectMappers.dataObjectMappers;

import com.cver.team.model.data.Certificate;
import com.cver.team.model.data.Experience;
import com.cver.team.model.data.Expertise;
import com.cver.team.persistence.jena.namespaces.CVO;
import com.cver.team.persistence.jena.objectMappers.entityObjectMappers.CertificateCardObjectMapper;
import com.cver.team.persistence.jena.objectMappers.entityObjectMappers.DocumentObjectMapper;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.vocabulary.RDF;

/**
 * Created by PC on 25/08/2016.
 */
public class CertificateObjectMapper {
    public static Certificate generateCertificate(Model model, String uri) {
        Resource certificate = model.getResource(uri);
        if (certificate != null)
            return generateCertificate(model, certificate);
        return null;
    }

    public static Certificate generateCertificate(Model model, Resource resource) {
        Certificate certificate = new Certificate();
        certificate = CertificateCardObjectMapper.generateCertificate(model, resource, certificate);
        certificate = DocumentObjectMapper.generateDocument(model, resource, certificate);

        // Expertise
        Statement statement = resource.getProperty(CVO.getProperty("forExpertise"));
        if (statement != null)
            certificate.setForExpertise(getExpertise(model, statement.getObject().asResource()));

        return certificate;
    }

    private static Expertise getExpertise(Model model, Resource resource) {
        if (resource.hasProperty(RDF.type, CVO.getResource("Experience")))
            return ExperienceObjectMapper.generateExperience(model, resource);
        else return SkillObjectMapper.generateSkill(model, resource);
    }
}
