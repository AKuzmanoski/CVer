package persistence.semantic;

import model.Cv;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.SimpleLog;
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
        return new Cv("Aleksandar", "Kuzmanoski");
    }

    public Cv createCv(Cv cv) {
        Log log = new SimpleLog("My Logger");
        log.info(cv.toString());
        System.out.println(cv);
        return cv;
    }
}
