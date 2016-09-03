package com.cver.team.persistence.jena;

import com.cver.team.model.entity.CV;
import com.cver.team.persistence.CvRepository;
import com.cver.team.persistence.jena.helper.JenaPreferences;
import com.cver.team.persistence.jena.namespaces.CVR;
import com.cver.team.persistence.jena.objectMappers.entityObjectMappers.CvObjectMapper;
import com.cver.team.persistence.jena.queries.Queries;
import org.apache.jena.query.ParameterizedSparqlString;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.sparql.SystemARQ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by PC on 25/08/2016.
 */
@Repository
public class CvRepositoryJena implements CvRepository {
    @Autowired
    QueryRepository queryRepository;

    @Override
    public CV getCv(Long id) {
        return null;
    }

    @Override
    public CV createCv(CV cv) {
        return null;
    }

    @Override
    public List<CV> getAllCvs() {
        return null;
    }

    @Override
    public CV getCv(String id) {
        String uri = CVR.getURI(id);
        ParameterizedSparqlString queryString = new ParameterizedSparqlString();
        queryString.setCommandText(queryRepository.getQuery(Queries.getCV));
        queryString.setIri("cv", uri);

        Query query = queryString.asQuery();
        QueryExecution queryExecution = QueryExecutionFactory.sparqlService(JenaPreferences.SPARQLEndpoint, query);
        Model model = queryExecution.execConstruct();
        CV cv = CvObjectMapper.generateCv(model, uri);
        return cv;
    }

    @Override
    public CV save(CV cv) {
        return null;
    }

    @Override
    public void delete(CV cv) {

    }

    @Override
    public void delete(String account) {

    }

    @Override
    public CV getNewCv(String userId) {

        ParameterizedSparqlString queryString = new ParameterizedSparqlString();
        queryString.setCommandText(queryRepository.getQuery(Queries.getNewCV));
        if (userId != null) {
            String uri = CVR.getURI(userId);
            queryString.setIri("person", uri);
        }
        else {
            String uri = CVR.getURI("johnDoe");
            queryString.setIri("person", uri);
        }
        String uri = CVR.getURI("newCv");
        queryString.setIri("newCv", uri);

        Query query = queryString.asQuery();
        QueryExecution queryExecution = QueryExecutionFactory.sparqlService(JenaPreferences.SPARQLEndpoint, query);
        Model model = queryExecution.execConstruct();
        model.write(System.out, "TURTLE");
        CV cv = CvObjectMapper.generateCv(model, uri);
        return cv;
    }
}
