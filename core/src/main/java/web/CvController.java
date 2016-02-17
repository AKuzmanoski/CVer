package web;

import model.Cv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.*;
import services.CvService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author CVerTeam
 * @version 1.0
 * @since 1/17/2016
 */
@RestController
@RequestMapping(value = "/cvs", produces = MediaType.APPLICATION_JSON_VALUE)
public class CvController {
    @Autowired
    CvService cvService;
/*
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Cv getCv(@PathVariable Long id) {
        return cvService.getCv(id);
    }*/

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public
    @ResponseBody
    Cv createCv(@RequestBody Cv cv, HttpServletResponse response) throws BindException {
        System.out.println(cv);
        Cv newCv = cvService.createCv(cv);
        response.setHeader("Location", "/cvs/" + newCv.getAccount());
        return newCv;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Cv> getCvs() {
        return cvService.getAllCvs();
    }

    @RequestMapping(value = "/{account}", method = RequestMethod.GET)
    public Cv getCv(@PathVariable String account) {
        return cvService.getCv(account);
    }

    @RequestMapping(value = "/{account}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void saveCv(@PathVariable String account, @RequestBody Cv cv) {
        cvService.save(cv);
    }

    @RequestMapping(value = "/{account}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCv(@PathVariable("account") String account) {
        cvService.delete(account);
    }
}