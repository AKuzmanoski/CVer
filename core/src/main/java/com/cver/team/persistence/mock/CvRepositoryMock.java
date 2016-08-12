package com.cver.team.persistence.mock;

import com.cver.team.model.entity.Cv;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.SimpleLog;
import org.springframework.stereotype.Repository;
import com.cver.team.persistence.CvRepository;

import java.util.List;

/**
 * This is just Mock implementation of {@link CvRepository} interface.
 *
 * @author CVerTeam
 * @version 1.0
 * @since 1/17/2016
 */
@Repository
public class CvRepositoryMock implements CvRepository {
    public Cv getCv(Long id) {
        return new Cv("Aleksandar", "Kuzmanoski");
    }

    public Cv createCv(Cv cv) {
        Log log = new SimpleLog("My Logger");
        log.info(cv.toString());
        System.out.println(cv);
        return cv;
    }

    @Override
    public List<Cv> getAllCvs() {
        return null;
    }

    @Override
    public Cv getCv(String account) {
        return null;
    }

    @Override
    public Cv save(Cv cv) {
        return null;
    }

    @Override
    public void delete(Cv cv) {

    }

    @Override
    public void delete(String account) {

    }
}
