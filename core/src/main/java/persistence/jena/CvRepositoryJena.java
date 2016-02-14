package persistence.jena;

import model.Cv;
import model.helper.CvNullable;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.SimpleLog;
import org.apache.jena.query.*;
import org.apache.jena.vocabulary.VCARD;
import org.springframework.stereotype.Repository;
import persistence.CvRepository;
import persistence.jena.helper.JenaPreferences;
import persistence.jena.helper.SPARQLPrefix;
import persistence.jena.helper.URIMaker;

import java.util.ArrayList;
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
     * @param id of the cv that is requested.
     * @return cv associated with the id.
     */
    public Cv getCv(Long id) {
        return new CvNullable();
    }

    public Cv getCv(String account) {
        String cvURI = URIMaker.getCvr(account);
        StringBuilder queryString = new StringBuilder();
        queryString.append(SPARQLPrefix.foaf);
        queryString.append("SELECT ?givenName ?familyName ");
        queryString.append("WHERE { ");
        queryString.append(cvURI);
        queryString.append(" foaf:givenName ?givenName ; ");
        queryString.append("foaf:familyName ?familyName . ");
        queryString.append("}");
        Query query = QueryFactory.create(queryString.toString());
        try (QueryExecution queryExecution = QueryExecutionFactory.sparqlService(JenaPreferences.SPARQLEndpoint, query)) {
            ResultSet resultSet = queryExecution.execSelect();
            if (resultSet.hasNext()) {
                QuerySolution querySolution = resultSet.nextSolution();
                Cv cv = new Cv();
                cv.setFirstName(querySolution.get("givenName").toString());
                cv.setLastName(querySolution.get("familyName").toString());
                cv.setAccount(account);
                return cv;
            }
        }
        return new CvNullable();
    }

    public Cv createCv(Cv cv) {
        Log log = new SimpleLog("My Logger");
        log.info(cv.toString());
        System.out.println(cv);
        return cv;
    }

    public List<Cv> getAllCvs() {
        List<Cv> cvs = new ArrayList<>();
        Cv cv;
        String queryString = SPARQLPrefix.foaf +
                "SELECT ?person ?givenName ?familyName " +
                "WHERE { " +
                "?person a foaf:Person ; " +
                "foaf:givenName ?givenName ; " +
                "foaf:familyName ?familyName . " +
                "}";
        Query query = QueryFactory.create(queryString);
        try (QueryExecution queryExecution = QueryExecutionFactory.sparqlService(JenaPreferences.SPARQLEndpoint, query)) {
            ResultSet resultSet = queryExecution.execSelect();
            while (resultSet.hasNext()) {
                QuerySolution querySolution = resultSet.nextSolution();
                cv = new Cv();
                cv.setFirstName(querySolution.get("givenName").toString());
                cv.setLastName(querySolution.get("familyName").toString());
                cvs.add(cv);
            }
        }
        return cvs;
    }
}