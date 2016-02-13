package persistence.jena;

import model.Cv;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.SimpleLog;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.impl.PropertyImpl;
import org.springframework.stereotype.Repository;
import persistence.CvRepository;

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
    private static final String cvr = "http://cver.com/resource/";
    private static final String foaf = "http://xmlns.com/foaf/0.1/";

    public Cv getCv(Long id) {
        String cvUri = "https://www.dropbox.com/s/pi0y24wr8a2op3t/AleksandarKuzmanoski.ttl?dl=1";
        String personUri = cvr + "AleksandarKuzmanoski";


        Model cv = ModelFactory.createDefaultModel();
        cv.read(cvUri, "TURTLE");
        Resource person = cv.getResource(personUri);
        cv.write(System.out, "TURTLE");
        cv.write(System.out, "RDF/XML");
        cv.write(System.out, "N-TRIPLE");
        Statement givenName = person.getProperty(new PropertyImpl(foaf + "givenName"));
        Statement familyName = person.getProperty(new PropertyImpl(foaf + "familyName"));
        String lastName = familyName.getObject().toString();
        String firstName = givenName.getObject().toString();
        return new Cv(firstName, lastName);
        //return new Cv("Aleksandar", "Kuzmanoski");
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
        String queryString = "prefix foaf:  <http://xmlns.com/foaf/0.1/> " +
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
