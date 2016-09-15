package com.cver.team.persistence.jena;

import com.cver.team.model.entity.Call;
import com.cver.team.model.entity.Person;
import com.cver.team.model.literal.Identifier;
import com.cver.team.persistence.CallRepository;
import com.cver.team.persistence.helper.URIMaker;
import com.cver.team.persistence.jena.helper.DateTimeConverter;
import com.cver.team.persistence.jena.helper.IdentifierGenerator;
import com.cver.team.persistence.jena.helper.JenaPreferences;
import com.cver.team.persistence.jena.namespaces.CVO;
import com.cver.team.persistence.jena.namespaces.CVR;
import com.cver.team.persistence.jena.objectMappers.entityObjectMappers.CallObjectMapper;
import com.cver.team.persistence.jena.objectMappers.entityObjectMappers.PersonObjectMapper;
import com.cver.team.persistence.jena.queries.Queries;
import org.apache.jena.datatypes.xsd.XSDDatatype;
import org.apache.jena.query.ParameterizedSparqlString;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.update.UpdateExecutionFactory;
import org.apache.jena.update.UpdateProcessor;
import org.apache.jena.update.UpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * Created by PC on 05/09/2016.
 */
@Repository
public class CallRepositoryJena implements CallRepository {
    @Autowired
    QueryRepository queryRepository;
    @Autowired
    CallObjectMapper callObjectMapper;

    @Override
    public Call getCall(String id) {
        String uri = CVR.getURI(id);
        ParameterizedSparqlString queryString = new ParameterizedSparqlString();
        queryString.setCommandText(queryRepository.getQuery(Queries.getCall));
        queryString.setIri("call", uri);

        Query query = queryString.asQuery();
        QueryExecution queryExecution = QueryExecutionFactory.sparqlService(JenaPreferences.SPARQLEndpoint, query);
        Model model = queryExecution.execConstruct();
        Call call = callObjectMapper.generateCall(model, uri);
        return call;
    }

    public Call insertCall(Call call, String organizationId) {
        call.setIdentifier(IdentifierGenerator.generateIdentifier());
        Date date = new Date();

        ParameterizedSparqlString queryString = new ParameterizedSparqlString();
        queryString.setCommandText(queryRepository.getQuery(Queries.insertCall));

        queryString.setIri("organization", CVR.getURI(organizationId));
        queryString.setIri("call", call.getIdentifier().getURI());
        queryString.setLiteral("creationDate", DateTimeConverter.getCalendar(date));
        queryString.setLiteral("lastModified", DateTimeConverter.getCalendar(date));
        queryString.setLiteral("public", call.isPublic());
        if (call.getCoverPictureUrl() != null) {
            queryString.setIri("cover", CVR.generateURI());
            queryString.setIri("coverType", CVO.getURI("Image"));
            queryString.setLiteral("coverUrl", call.getCoverPictureUrl());
            queryString.setLiteral("coverCreationDate", DateTimeConverter.getCalendar(date));
            queryString.setLiteral("coverLastModified", DateTimeConverter.getCalendar(date));
        }

        if (call.getName() != null)
            queryString.setLiteral("name", call.getName());
        if (call.getDescription() != null)
            queryString.setLiteral("description", call.getDescription());

        UpdateRequest updateRequest = queryString.asUpdate();
        UpdateProcessor updateProcessor = UpdateExecutionFactory.createRemoteForm(updateRequest, JenaPreferences.UpdateEndpoint);
        updateProcessor.execute();

        return call;
    }

    public void apply(String cvId, String callId) {
        ParameterizedSparqlString queryString = new ParameterizedSparqlString();
        queryString.setCommandText(queryRepository.getQuery(Queries.applyToCall));

        queryString.setIri("cv", CVR.getURI(cvId));
        queryString.setIri("call", CVR.getURI(callId));


        UpdateRequest updateRequest = queryString.asUpdate();
        UpdateProcessor updateProcessor = UpdateExecutionFactory.createRemoteForm(updateRequest, JenaPreferences.UpdateEndpoint);
        updateProcessor.execute();
    }
}
