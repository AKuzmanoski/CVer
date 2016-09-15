package com.cver.team.persistence.jena.objectMappers.dataObjectMappers;

import com.cver.team.model.data.Certificate;
import com.cver.team.model.data.Experience;
import com.cver.team.model.data.Expertise;
import com.cver.team.model.data.Skill;
import com.cver.team.persistence.jena.helper.IdentifierGenerator;
import com.cver.team.persistence.jena.namespaces.CVO;
import com.cver.team.persistence.jena.namespaces.CVR;
import com.cver.team.persistence.jena.objectMappers.entityObjectMappers.AgentObjectMapper;
import com.cver.team.persistence.jena.objectMappers.entityObjectMappers.CertificateCardObjectMapper;
import com.cver.team.persistence.jena.objectMappers.entityObjectMappers.DocumentObjectMapper;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.vocabulary.RDF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by PC on 25/08/2016.
 */
@Component
public class CertificateObjectMapper {
    @Autowired
    CertificateCardObjectMapper certificateCardObjectMapper;
    @Autowired
    ExperienceObjectMapper experienceObjectMapper;
    @Autowired
    SkillObjectMapper skillObjectMapper;
    @Autowired
    DocumentObjectMapper documentObjectMapper;

    public Certificate generateCertificate(Model model, String uri) {
        Resource certificate = model.getResource(uri);
        if (certificate != null)
            return generateCertificate(model, certificate);
        return null;
    }

    public Certificate generateCertificate(Model model, Resource resource) {
        Certificate certificate = new Certificate();
        certificate = certificateCardObjectMapper.generateCertificate(model, resource, certificate);
        certificate = documentObjectMapper.generateDocument(model, resource, certificate);

        // Expertise
        Statement statement = resource.getProperty(CVO.getProperty("forExpertise"));
        if (statement != null)
            certificate.setForExpertise(getExpertise(model, statement.getObject().asResource()));

        return certificate;
    }

    private Expertise getExpertise(Model model, Resource resource) {
        if (resource.hasProperty(RDF.type, CVO.getResource("Experience")))
            return experienceObjectMapper.generateExperience(model, resource);
        else return skillObjectMapper.generateSkill(model, resource);
    }

    public void createModel(Certificate certificate, Model model) {
        if (certificate.getIdentifier() != null)
            return;
        certificate.setIdentifier(IdentifierGenerator.generateIdentifier());
        Resource resource = CVR.getResource(certificate.getIdentifier().getId());
        createModel(certificate, model, resource);
    }

    public <T extends Certificate> void createModel(T certificate, Model model, Resource resource) {
        documentObjectMapper.createModel(certificate, model, resource);
        certificateCardObjectMapper.createModel(certificate, model, resource);

        if (certificate.getForExpertise() != null) {
            if (certificate.getForExpertise() instanceof Experience)
                experienceObjectMapper.createModel((Experience)certificate.getForExpertise(), model);
            if (certificate.getForExpertise() instanceof Skill)
                skillObjectMapper.createModel((Skill)certificate.getForExpertise(), model);
            model.add(resource, CVO.getProperty("forExpertise"), CVR.getResource(certificate.getIdentifier().getId()));
        }
    }
}
