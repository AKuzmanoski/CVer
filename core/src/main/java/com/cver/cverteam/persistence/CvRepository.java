package com.cver.cverteam.persistence;

import com.cver.cverteam.model.Cv;

import java.util.List;

/**
 * @author CVerTeam
 * @version 1.0
 * @since 1/17/2016
 */
public interface CvRepository {
    Cv getCv(Long id);

    Cv createCv(Cv cv);

    List<Cv> getAllCvs();

    Cv getCv(String account);

    Cv save(Cv cv);

    void delete(Cv cv);

    void delete(String account);
}
