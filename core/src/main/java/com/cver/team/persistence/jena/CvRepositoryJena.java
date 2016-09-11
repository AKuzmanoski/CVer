package com.cver.team.persistence.jena;

import com.cver.team.model.entity.CV;
import com.cver.team.persistence.CvRepository;
import com.cver.team.persistence.jena.helper.JenaPreferences;
import com.cver.team.persistence.jena.namespaces.CVO;
import com.cver.team.persistence.jena.namespaces.CVR;
import com.cver.team.persistence.jena.objectMappers.entityObjectMappers.CvObjectMapper;
import com.cver.team.persistence.jena.queries.Queries;
import org.apache.jena.query.ParameterizedSparqlString;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.sparql.SystemARQ;
import org.apache.jena.update.UpdateExecutionFactory;
import org.apache.jena.update.UpdateProcessor;
import org.apache.jena.update.UpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.StringWriter;
import java.util.List;

/**
 * Created by PC on 25/08/2016.
 */
@Repository
public class CvRepositoryJena implements CvRepository {
    @Autowired
    QueryRepository queryRepository;
    @Autowired
    CvObjectMapper cvObjectMapper;

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
        CV cv = cvObjectMapper.generateCv(model, uri);
        return cv;
    }

    @Override
    public CV save(CV cv) {
        Model model = ModelFactory.createDefaultModel();
        cvObjectMapper.createModel(cv, model);

        StringWriter writer = new StringWriter();

        model.write(writer, "TURTLE");

        String commandText = "INSERT {" + writer.getBuffer().toString() + "} WHERE {}";
        ParameterizedSparqlString queryString = new ParameterizedSparqlString();
        queryString.setCommandText(commandText);

        UpdateRequest updateRequest = queryString.asUpdate();
        UpdateProcessor updateProcessor = UpdateExecutionFactory.createRemoteForm(updateRequest, JenaPreferences.UpdateEndpoint);
        updateProcessor.execute();

        return cv;
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
        CV cv = cvObjectMapper.generateCv(model, uri);
        cv.setIdentifier(null);
        return cv;
    }

    @Override
    public CV update(CV cv) {
        CV oldCv = getCv(cv.getIdentifier().getId());
        Model insert = ModelFactory.createDefaultModel();
        Model delete = ModelFactory.createDefaultModel();
        cvObjectMapper.updateModel(oldCv, cv, insert, delete);

        StringWriter insertWriter = new StringWriter();
        insert.write(insertWriter, "TURTLE");

        StringWriter deleteWriter = new StringWriter();
        delete.write(deleteWriter, "TURTLE");

        String commandText = "DELETE {" + deleteWriter.getBuffer().toString() + "} INSERT {" + insertWriter.getBuffer().toString() + "} WHERE {}";
        ParameterizedSparqlString queryString = new ParameterizedSparqlString();
        queryString.setCommandText(commandText);

        System.out.println(queryString.toString());

        UpdateRequest updateRequest = queryString.asUpdate();
        UpdateProcessor updateProcessor = UpdateExecutionFactory.createRemoteForm(updateRequest, JenaPreferences.UpdateEndpoint);
        updateProcessor.execute();

        return cv;
    }
}
