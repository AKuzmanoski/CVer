package com.cver.team.persistence.jena;

import com.cver.team.model.data.Certificate;
import com.cver.team.model.data.CertificateCard;
import com.cver.team.model.entity.Person;
import com.cver.team.persistence.CertificateRepository;
import com.cver.team.persistence.jena.helper.JenaPreferences;
import com.cver.team.persistence.jena.namespaces.CVR;
import com.cver.team.persistence.jena.objectMappers.dataObjectMappers.CertificateObjectMapper;
import com.cver.team.persistence.jena.queries.Queries;
import com.github.andrewoma.dexx.collection.List;
import org.apache.jena.query.ParameterizedSparqlString;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.rdf.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class CertificateRepositoryJena implements CertificateRepository {
    @Autowired
    QueryRepository queryRepository;

    @Override
    public CertificateCard saveNewCertificate(CertificateCard certificateCard) {

        String language = "en";

//        String personURI = certificateCard.getRecipientURI();
//        String certificateType = certificateCard.getCertificateType();
//        String description = certificateCard.getDescription();
//        String issuerURI = certificateCard.getIssuerURI();
//        String certificateID = UUID.randomUUID().toString();
//        String certificateURI = ResourcePrefixes.CERTIFICATE_PREFIX+certificateID;
//
//        ParameterizedSparqlString queryString = new ParameterizedSparqlString();
//
//        queryString.setNsPrefix("cver", SPARQLPrefix.cvr);
//        queryString.setNsPrefix("cvo", SPARQLPrefix.cvo);
//
//        queryString.setCommandText("INSERT { \n " +
//                " ?personURI cvo:hasCertificate ?certificateCard . \n " +
//                " ?certificateCard cvo:description ?desc ; \n " +
//                "              cvo:issuer ?issuerURI ; \n " +
//                "              cvo:recipient ?personURI . \n" +
//                " } WHERE { } "
//
//        );
//
//
//
//        queryString.setIri("personURI",personURI);
//
//        if(description != null)
//        queryString.setLiteral("?desc",description,language);
//
//        if(issuerURI != null)
//        queryString.setIri("?issuerURI",issuerURI);
//
//        queryString.setIri("certificateCard",certificateURI);
//
//        System.out.println(queryString.toString());
//
//        UpdateRequest updateRequest = UpdateFactory.create(queryString.toString());
//        UpdateProcessor updateProcessor = UpdateExecutionFactory.createRemote(updateRequest, JenaPreferences.UpdateEndpoint);
//        updateProcessor.execute();
//
//        Identifier identifier = new Identifier();
//        identifier.setId(certificateID);
//        identifier.setURI(certificateURI);
//        certificateCard.setIdentifier(identifier);
        return certificateCard;
    }

    @Override
    public CertificateCard deleteCertificate(CertificateCard certificateCard) {
        return null;
    }

    @Override
    public List<CertificateCard> getCertificatesForPerson(Person owner) {
        return null;
    }

    @Override
    public Certificate getCertificate(String id) {
        String uri = CVR.getURI(id);
        ParameterizedSparqlString queryString = new ParameterizedSparqlString();
        queryString.setCommandText(queryRepository.getQuery(Queries.getCertificate));
        queryString.setIri("certificate", uri);

        Query query = queryString.asQuery();
        QueryExecution queryExecution = QueryExecutionFactory.sparqlService(JenaPreferences.SPARQLEndpoint, query);
        Model model = queryExecution.execConstruct();
        Certificate certificate = CertificateObjectMapper.generateCertificate(model, uri);
        return certificate;
    }
}
