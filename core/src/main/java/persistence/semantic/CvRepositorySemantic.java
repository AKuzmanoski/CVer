package persistence.semantic;

import model.Cv;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.SimpleLog;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.impl.PropertyImpl;
import org.springframework.stereotype.Repository;
import persistence.CvRepository;

/**
 * This is just Semantic Web implementation of {@link CvRepository} interface.
 *
 * @author CVerTeam
 * @version 1.0
 * @since 1/17/2016
 */
@Repository
public class CvRepositorySemantic implements CvRepository {

    public Cv getCv(Long id) {
        String cvUri = "https://www.dropbox.com/s/nyrbr91vmfakuun/me.ttl?dl=1";
        String personUri = "https://www.dropbox.com/s/nyrbr91vmfakuun/Me";
        String foaf = "http://xmlns.com/foaf/0.1/";


        Model cv = ModelFactory.createDefaultModel();
        cv.read(cvUri, "TURTLE");
        Resource person = cv.getResource(personUri);
        cv.write(System.out, "TURTLE");
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
}
