package services.impl;

import model.Cv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import persistence.CvRepository;
import services.CvService;

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

    public Cv getCv(Long id) {
        return cvRepositoryJena.getCv(id);
    }

    public Cv createCv(@RequestBody Cv cv) {
        return cvRepositoryJena.createCv(cv);
    }

    @Override
    public List<Cv> getAllCvs() {
        return cvRepositoryJena.getAllCvs();
    }

    @Override
    public Cv getCv(String account) {
        return cvRepositoryJena.getCv(account);
    }

    @Override
    public Cv save(Cv cv) {
        return cvRepositoryJena.save(cv);
    }

    @Override
    public void delete(Cv cv) {
        cvRepositoryJena.delete(cv);
    }

    @Override
    public void delete(String account) {
        cvRepositoryJena.delete(account);
    }
}