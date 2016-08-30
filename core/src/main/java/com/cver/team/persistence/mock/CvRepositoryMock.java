package com.cver.team.persistence.mock;

import com.cver.team.model.entity.CV;
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
    public CV getCv(Long id) {
        return new CV();
    }

    public CV createCv(CV cv) {
        Log log = new SimpleLog("My Logger");
        log.info(cv.toString());
        System.out.println(cv);
        return cv;
    }

    @Override
    public List<CV> getAllCvs() {
        return null;
    }

    @Override
    public CV getCv(String id) {
        return null;
    }

    @Override
    public CV save(CV cv) {
        return null;
    }

    @Override
    public void delete(CV cv) {

    }

    @Override
    public void delete(String account) {

    }
}
