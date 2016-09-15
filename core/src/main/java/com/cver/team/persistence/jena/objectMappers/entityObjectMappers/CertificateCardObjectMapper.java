package com.cver.team.persistence.jena.objectMappers.entityObjectMappers;

import com.cver.team.model.data.Certificate;
import com.cver.team.model.data.CertificateCard;
import com.cver.team.persistence.jena.helper.DateTimeConverter;
import com.cver.team.persistence.jena.namespaces.CVO;
import com.cver.team.persistence.jena.namespaces.CVR;
import com.cver.team.persistence.jena.objectMappers.dataObjectMappers.DataObjectMapper;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.vocabulary.RDF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by PC on 17/08/2016.
 */
@Component
public class CertificateCardObjectMapper {
    @Autowired
    DataObjectMapper dataObjectMapper;
    @Autowired
    DocumentCardObjectMapper documentCardObjectMapper;
    @Autowired
    PersonObjectMapper personObjectMapper;
    @Autowired
    OrganizationObjectMapper organizationObjectMapper;
    @Autowired
    AgentObjectMapper agentObjectMapper;

    public CertificateCard generateCertificate(Model model, Resource resource) {
        CertificateCard certificateCard = new CertificateCard();

        return generateCertificate(model, resource, certificateCard);
    }

    public <T extends CertificateCard> T generateCertificate(Model model, Resource resource, T certificateCard) {
        certificateCard = documentCardObjectMapper.generateDocument(model, resource, certificateCard);
        certificateCard = dataObjectMapper.generateData(model, resource, certificateCard);

        Statement statement = resource.getProperty(CVO.getProperty("issuer"));
        if (statement != null) {
            Resource issuer = statement.getObject().asResource();
            if (issuer.hasProperty(RDF.type, CVO.getResource("Person")))
                certificateCard.setIssuer(personObjectMapper.generatePerson(model, issuer));
            else certificateCard.setIssuer(organizationObjectMapper.generateOrganization(model, issuer));
        }

        statement = resource.getProperty(CVO.getProperty("recipient"));
        if (statement != null) {
            Resource recipient = statement.getObject().asResource();
            if (recipient.hasProperty(RDF.type, CVO.getResource("Person")))
                certificateCard.setRecipient(personObjectMapper.generatePerson(model, recipient));
            else certificateCard.setRecipient(organizationObjectMapper.generateOrganization(model, recipient));
        }

        statement = resource.getProperty(CVO.getProperty("issuedDate"));
        if (statement != null)
            certificateCard.setIssuedDate(DateTimeConverter.getDate(statement.getObject()));


        return certificateCard;
    }

    public <T extends CertificateCard> void createModel(T certificate, Model model, Resource resource) {
        documentCardObjectMapper.createModel(certificate, model, resource);
        dataObjectMapper.createModel(certificate, model, resource);

        if (certificate.getIssuer() != null) {
            agentObjectMapper.createModel(certificate.getIssuer(), model);
            model.add(resource, CVO.getProperty("issuer"), CVR.getResource(certificate.getIssuer().getIdentifier().getId()));
        }

        if (certificate.getRecipient() != null) {
            agentObjectMapper.createModel(certificate.getRecipient(), model);
            model.add(resource, CVO.getProperty("recipient"), CVR.getResource(certificate.getRecipient().getIdentifier().getId()));
        }

        if (certificate.getIssuedDate() != null) {
            Literal literal = model.createTypedLiteral(DateTimeConverter.getCalendar(certificate.getIssuedDate()));
            model.add(resource, CVO.getProperty("issuedDate"), literal);
        }
    }
}
