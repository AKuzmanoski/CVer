package services;

import model.Cv;

import java.util.List;

/**
 * @author CVerTeam
 * @version 1.0
 * @since 1/17/2016
 */
public interface CvService {

    Cv getCv(Long id);

    Cv createCv(Cv cv);

    List<Cv> getAllCvs();

    Cv getCv(String account);

    Cv save(Cv cv);

    void delete(Cv cv);

    void delete(String account);
}