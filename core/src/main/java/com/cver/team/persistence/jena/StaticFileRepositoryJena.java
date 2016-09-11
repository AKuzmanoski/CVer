package com.cver.team.persistence.jena;

import com.cver.team.model.externalresource.StaticFile;
import com.cver.team.persistence.StaticFileRepository;
import com.cver.team.persistence.jena.helper.ByteConverter;
import com.cver.team.persistence.jena.helper.IdentifierGenerator;
import com.cver.team.persistence.jena.helper.JenaPreferences;
import com.cver.team.persistence.jena.namespaces.CVR;
import com.cver.team.persistence.jena.objectMappers.externalResourceObjectMappers.StaticFileObjectMapper;
import com.cver.team.persistence.jena.queries.Queries;
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

/**
 * Created by PC on 10/09/2016.
 */
@Repository
public class StaticFileRepositoryJena implements StaticFileRepository {
    @Autowired
    QueryRepository queryRepository;

    @Override
    public StaticFile get(String id) {
        String uri = CVR.getURI(id);
        ParameterizedSparqlString queryString = new ParameterizedSparqlString();
        queryString.setCommandText(queryRepository.getQuery(Queries.getPerson));
        queryString.setIri("person", uri);

        Query query = queryString.asQuery();
        QueryExecution queryExecution = QueryExecutionFactory.sparqlService(JenaPreferences.SPARQLEndpoint, query);
        Model model = queryExecution.execConstruct();
        StaticFile staticFile = StaticFileObjectMapper.generatePerson(model, uri);
        return staticFile;
    }

    @Override
    public StaticFile save(StaticFile staticFile) {
        staticFile.setIdentifier(IdentifierGenerator.generateIdentifier());

        ParameterizedSparqlString queryString = new ParameterizedSparqlString();
        queryString.setCommandText(queryRepository.getQuery(Queries.insertStaticFile));

        queryString.setIri("staticFile", staticFile.getIdentifier().getURI());

        queryString.setLiteral("contentType", staticFile.getContentType());

        if (staticFile.getName() != null)
            queryString.setLiteral("name", staticFile.getName());

        if (staticFile.getDescription() != null)
            queryString.setLiteral("description", staticFile.getDescription());

        queryString.setLiteral("url", staticFile.getUrl());

        queryString.setLiteral("val ", ByteConverter.convertBytesToString(staticFile.getValue()));

        UpdateRequest updateRequest = queryString.asUpdate();
        UpdateProcessor updateProcessor = UpdateExecutionFactory.createRemoteForm(updateRequest, JenaPreferences.UpdateEndpoint);
        updateProcessor.execute();

        return staticFile;
    }
}
