package com.cver.team.persistence.jena;

import com.cver.team.model.Cv;
import com.cver.team.model.helper.CvNullable;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.sparql.vocabulary.FOAF;
import org.apache.jena.update.UpdateExecutionFactory;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateProcessor;
import org.apache.jena.update.UpdateRequest;
import org.springframework.stereotype.Repository;
import com.cver.team.persistence.CvRepository;
import com.cver.team.persistence.helper.URIMaker;
import com.cver.team.persistence.jena.helper.CvObjectMapper;
import com.cver.team.persistence.jena.helper.CvsObjectMapper;
import com.cver.team.persistence.jena.helper.JenaPreferences;
import com.cver.team.persistence.jena.helper.namespaces.CVR;

import java.util.List;

/**
 * This is just Semantic Web implementation of {@link CvRepository} interface.
 *
 * @author CVerTeam
 * @version 1.0
 * @since 1/17/2016
 */
@Repository
public class CvRepositoryJena implements CvRepository {

    /**
     * Ids are crucial for relational databases. That is not the case here, thus this method returns nullable object.
     * Maybe in the feature we will try to implement this method.
     *
     * @param id of the cv that is requested.
     * @return cv associated with the id.
     */
    public Cv getCv(Long id) {
        return new CvNullable();
    }

    public Cv getCv(String account) {
        // Build Query
        ParameterizedSparqlString queryString = new ParameterizedSparqlString();
        queryString.setNsPrefix("foaf", FOAF.getURI());
        queryString.setCommandText("CONSTRUCT { ?cv ?predicate ?object } " +
                " WHERE {" +
                " ?cv ?predicate ?object ; " +
                " ?accountProperty ?account . " +
                "}");
        queryString.setLiteral("account", account);
        queryString.setIri("accountProperty", FOAF.holdsAccount.getURI());

        // Execute query
        Query query = QueryFactory.create(queryString.toString());
        QueryExecution queryExecution = QueryExecutionFactory.sparqlService(JenaPreferences.SPARQLEndpoint, query);
        Model model = queryExecution.execConstruct();
        Cv cv = CvObjectMapper.generateCv(model);
        return cv;
    }

    public Cv createCv(Cv cv) {
        // Generate Identifier
        String URI = generateUri();
        String CvrURI = CVR.getUri(URI);
        // Build Query
        ParameterizedSparqlString queryString = new ParameterizedSparqlString();
        queryString.setNsPrefix("cvr", CVR.getURI());
        queryString.setNsPrefix("foaf", FOAF.getURI());
        queryString.setCommandText("INSERT {" +
                " ?uri ?givenNameProperty ?givenName ; " +
                " ?familyNameProperty ?familyName ; " +
                " ?accountProperty ?account ; " +
                " a foaf:Person . " +
                "} WHERE { }");
        queryString.setLiteral("givenName", cv.getFirstName());
        queryString.setLiteral("familyName", cv.getLastName());
        queryString.setLiteral("account", cv.getAccount());
        queryString.setIri("accountProperty", FOAF.holdsAccount.getURI());
        queryString.setIri("givenNameProperty", FOAF.givenname.getURI());
        queryString.setIri("familyNameProperty", FOAF.family_name.getURI());
        queryString.setIri("uri", CvrURI);

        // EXECUTE QUERY
        System.out.println(queryString.toString());
        UpdateRequest updateRequest = UpdateFactory.create(queryString.toString());
        UpdateProcessor updateProcessor = UpdateExecutionFactory.createRemote(updateRequest, JenaPreferences.UpdateEndpoint);
        updateProcessor.execute();
        return cv;
    }

    public List<Cv> getAllCvs() {
        // Build Query
        ParameterizedSparqlString queryString = new ParameterizedSparqlString();
        queryString.setCommandText("CONSTRUCT { ?person ?predicate ?object . } " +
                "WHERE {" +
                " ?person ?predicate ?object ;" +
                " a foaf:Person . " +
                "}");
        queryString.setNsPrefix("foaf", FOAF.getURI());
        Query query = QueryFactory.create(queryString.toString());

        // Execute Query
        QueryExecution queryExecution = QueryExecutionFactory.sparqlService(JenaPreferences.SPARQLEndpoint, query);
        Model model = queryExecution.execConstruct();
        List<Cv> cvs = CvsObjectMapper.generateCvs(model);
        return cvs;
    }

    @Override
    public Cv save(Cv cv) {
        return new CvNullable();
    }

    @Override
    public void delete(Cv cv) {
        delete(cv.getAccount());
    }

    @Override
    public void delete(String account) {
        ParameterizedSparqlString queryString = new ParameterizedSparqlString();
        queryString.setCommandText("DELETE WHERE {" +
                " ?person ?property ?object ; " +
                " ?accountProperty ?account . " +
                "}");
        queryString.setNsPrefix("foaf", FOAF.getURI());
        queryString.setLiteral("account", account);
        queryString.setIri("accountProperty", FOAF.holdsAccount.getURI());
        UpdateRequest updateRequest = UpdateFactory.create(queryString.toString());
        UpdateProcessor updateProcessor = UpdateExecutionFactory.createRemote(updateRequest, JenaPreferences.UpdateEndpoint);
        updateProcessor.execute();
    }

    private String generateUri() {
        return "cv" + URIMaker.generateUri();
    }

    private String generateUri(String name) {
        return "cv" + URIMaker.generateUri(name);
    }
}