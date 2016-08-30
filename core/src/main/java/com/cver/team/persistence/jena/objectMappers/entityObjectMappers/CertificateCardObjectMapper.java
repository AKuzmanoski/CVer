package com.cver.team.persistence.jena.objectMappers.entityObjectMappers;

import com.cver.team.model.data.Certificate;
import com.cver.team.model.data.CertificateCard;
import com.cver.team.persistence.jena.helper.DateTimeConverter;
import com.cver.team.persistence.jena.namespaces.CVO;
import com.cver.team.persistence.jena.objectMappers.dataObjectMappers.DataObjectMapper;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.vocabulary.RDF;

/**
 * Created by PC on 17/08/2016.
 */
public class CertificateCardObjectMapper {
    public static CertificateCard generateCertificate(Model model, Resource resource) {
        CertificateCard certificateCard = new CertificateCard();

        return generateCertificate(model, resource, certificateCard);
    }

    public static <T extends CertificateCard> T generateCertificate(Model model, Resource resource, T certificateCard) {
        certificateCard = DocumentCardObjectMapper.generateDocument(model, resource, certificateCard);
        certificateCard = DataObjectMapper.generateData(model, resource, certificateCard);

        Statement statement = resource.getProperty(CVO.getProperty("issuer"));
        if (statement != null) {
            Resource issuer = statement.getObject().asResource();
            if (issuer.hasProperty(RDF.type, CVO.getResource("Person")))
                certificateCard.setIssuer(PersonObjectMapper.generatePerson(model, issuer));
            else certificateCard.setIssuer(OrganizationObjectMapper.generateOrganization(model, issuer));
        }

        statement = resource.getProperty(CVO.getProperty("recipient"));
        if (statement != null) {
            Resource recipient = statement.getObject().asResource();
            if (recipient.hasProperty(RDF.type, CVO.getResource("Person")))
                certificateCard.setRecipient(PersonObjectMapper.generatePerson(model, recipient));
            else certificateCard.setRecipient(OrganizationObjectMapper.generateOrganization(model, recipient));
        }

        statement = resource.getProperty(CVO.getProperty("issuedDate"));
        if (statement != null)
            certificateCard.setIssuedDate(DateTimeConverter.getDate(statement.getObject()));


        return certificateCard;
    }
}
