package com.cver.team.web;

import com.cver.team.model.entity.CV;
import com.cver.team.model.entity.Person;
import com.cver.team.services.CvService;
import com.cver.team.web.ResponseExceptions.OperationNotAuthorizedException;
import com.cver.team.web.ResponseExceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @author CVerTeam
 * @version 1.0
 * @since 1/17/2016
 */
@RestController
@RequestMapping(value = "/cv")
public class CvController {
    @Autowired
    CvService cvService;
/*
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public CV getCv(@PathVariable Long id) {
        return cvService.getCv(id);
    }*/

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public CV saveCv(@RequestBody CV cv, HttpSession session) throws BindException {
        Person person = (Person) session.getAttribute("user");
        if (person != null) {
            cv.setOwner(person);
            CV newCv = cvService.save(cv);
            //   response.setHeader("Location", "/cvs/" + newCv.getAccount());
            return newCv;
        }
        throw new OperationNotAuthorizedException();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.CREATED)
    public void updateCv(@RequestBody CV cv, @PathVariable String id, HttpSession session) throws BindException {

        CV newCv = cvService.update(cv);

    }


    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public CV getNewCv(HttpSession session) {
        Person user = (Person) session.getAttribute("user");
        if (user != null)
            return cvService.getNewCv(user.getIdentifier().getId());
        return cvService.getNewCv(null);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public CV getCv(@PathVariable String id) {
        CV cv = cvService.getCv(id);
        if (cv == null)
            throw new ResourceNotFoundException();
        return cv;
    }

    @RequestMapping(value = "/{account}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCv(@PathVariable("account") String account) {
        cvService.delete(account);
    }
}