package com.cver.team.persistence;

import com.cver.team.model.entity.CV;

import java.util.List;

/**
 * @author CVerTeam
 * @version 1.0
 * @since 1/17/2016
 */
public interface CvRepository {
    CV getCv(Long id);

    CV createCv(CV cv);

    List<CV> getAllCvs();

    CV getCv(String id);

    CV save(CV cv);

    void delete(CV cv);

    void delete(String account);

    CV getNewCv(String userId);
}
