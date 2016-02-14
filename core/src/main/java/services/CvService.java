package services;

import model.Cv;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author CVerTeam
 * @version 1.0
 * @since 1/17/2016
 */
public interface CvService {

    Cv getCv(Long id);

    Cv createCv(@RequestBody Cv cv);

    List<Cv> getAllCvs();

    Cv getCv(String account);
}
