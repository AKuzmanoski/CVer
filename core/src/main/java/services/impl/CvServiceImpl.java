package services.impl;

import model.Cv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import persistence.CvRepository;
import services.CvService;

/**
 * @author CVerTeam
 * @version 1.0
 * @since 1/17/2016
 */
@Service
public class CvServiceImpl implements CvService {
    @Autowired
    CvRepository cvRepository;

    public Cv getCv(Long id) {
        return cvRepository.getCv(id);
    }

    public Cv createCv(@RequestBody Cv person) {
        return cvRepository.createCv(person);
    }
}