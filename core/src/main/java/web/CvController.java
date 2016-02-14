package web;

import model.Cv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.*;
import services.CvService;

import java.util.List;

/**
 * @author CVerTeam
 * @version 1.0
 * @since 1/17/2016
 */
@RestController
@RequestMapping(value = "/cv", produces = MediaType.APPLICATION_JSON_VALUE)
public class CvController {
    @Autowired
    CvService cvService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Cv getCv(@PathVariable Long id) {
        return cvService.getCv(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Cv createPerson(@RequestBody Cv cv) throws BindException {
        return cvService.createCv(cv);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Cv> getCv() {
        return cvService.getAllCvs();
    }

    @RequestMapping(value = "/{account}", method = RequestMethod.GET)
    public Cv getCv(@PathVariable String account) {
        return cvService.getCv(account);
    }

}
