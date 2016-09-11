package com.cver.team.services.impl;

import com.cver.team.model.entity.CV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import com.cver.team.persistence.CvRepository;
import com.cver.team.services.CvService;

import java.util.List;

/**
 * @author CVerTeam
 * @version 1.0
 * @since 1/17/2016
 */
@Service
public class CvServiceImpl implements CvService {

    @Autowired
    CvRepository cvRepositoryJena;

    public CV getCv(Long id) {
        return cvRepositoryJena.getCv(id);
    }

    public CV createCv(@RequestBody CV cv) {
        return cvRepositoryJena.createCv(cv);
    }

    @Override
    public List<CV> getAllCvs() {
        return cvRepositoryJena.getAllCvs();
    }

    @Override
    public CV getCv(String id) {
        return cvRepositoryJena.getCv(id);
    }

    @Override
    public CV save(CV cv) {
        return cvRepositoryJena.save(cv);
    }

    @Override
    public void delete(CV cv) {
        cvRepositoryJena.delete(cv);
    }

    @Override
    public void delete(String account) {
        cvRepositoryJena.delete(account);
    }

    @Override
    public CV getNewCv(String userId) {
        return cvRepositoryJena.getNewCv(userId);
    }

    @Override
    public CV update(CV cv) {
        return cvRepositoryJena.update(cv);
    }
}