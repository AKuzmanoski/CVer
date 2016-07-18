package com.cver.team.persistence.jena;

import com.cver.team.model.Certificate;
import com.cver.team.model.Person;
import com.cver.team.model.literal.Identifier;
import com.cver.team.persistence.CertificateRepository;
import com.cver.team.persistence.jena.helper.JenaPreferences;
import com.cver.team.persistence.jena.helper.ResourcePrefixes;
import com.cver.team.persistence.jena.helper.SPARQLPrefix;
import com.github.andrewoma.dexx.collection.List;
import org.apache.jena.query.ParameterizedSparqlString;
import org.apache.jena.update.UpdateExecutionFactory;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateProcessor;
import org.apache.jena.update.UpdateRequest;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public class CertificateRepositoryJena implements CertificateRepository {

    @Override
    public Certificate saveNewCertificate(Certificate certificate) {

        String language = "en";

        String personURI = certificate.getRecipientURI();
        String certificateType = certificate.getCertificateType();
        String description = certificate.getDescription();
        String issuerURI = certificate.getIssuerURI();
        String certificateID = UUID.randomUUID().toString();
        String certificateURI = ResourcePrefixes.CERTIFICATE_PREFIX+certificateID;

        ParameterizedSparqlString queryString = new ParameterizedSparqlString();

        queryString.setNsPrefix("cver", SPARQLPrefix.cvr);
        queryString.setNsPrefix("cvo", SPARQLPrefix.cvo);

        queryString.setCommandText("INSERT { \n " +
                " ?personURI cvo:hasCertificate ?certificate . \n " +
                " ?certificate cvo:description ?desc ; \n " +
                "              cvo:issuer ?issuerURI ; \n " +
                "              cvo:recipient ?personURI . \n" +
                " } WHERE { } "

        );



        queryString.setIri("personURI",personURI);

        if(description != null)
        queryString.setLiteral("?desc",description,language);

        if(issuerURI != null)
        queryString.setIri("?issuerURI",issuerURI);

        queryString.setIri("certificate",certificateURI);

        System.out.println(queryString.toString());

        UpdateRequest updateRequest = UpdateFactory.create(queryString.toString());
        UpdateProcessor updateProcessor = UpdateExecutionFactory.createRemote(updateRequest, JenaPreferences.UpdateEndpoint);
        updateProcessor.execute();

        Identifier identifier = new Identifier();
        identifier.setId(certificateID);
        identifier.setURI(certificateURI);
        certificate.setIdentifier(identifier);
        return certificate;
    }

    @Override
    public Certificate deleteCertificate(Certificate certificate) {
        return null;
    }

    @Override
    public List<Certificate> getCertificatesForPerson(Person owner) {
        return null;
    }
}
