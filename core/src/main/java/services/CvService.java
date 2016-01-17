package services;

import model.Cv;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author CVerTeam
 * @version 1.0
 * @since 1/17/2016
 */
public interface CvService {

    Cv getCv(Long id);

    Cv createCv(@RequestBody Cv person);
}
