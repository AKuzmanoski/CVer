package com.cver.team.web;

import com.cver.team.model.data.Data;
import com.cver.team.model.entity.Person;
import com.cver.team.services.DataService;
import com.cver.team.web.ResponseExceptions.OperationNotAuthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by PC on 26/08/2016.
 */
@RestController
@RequestMapping("/data")
public class DataController {
    @Autowired
    DataService dataService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Data> getData(@RequestParam String query,
                              @RequestParam(required = false) String type,
                              @RequestParam Integer offset,
                              @RequestParam Integer limit, HttpSession session) {
        Person person = (Person) session.getAttribute("user");
        if (person != null)
            return dataService.queryData(query, type, offset, limit, person.getIdentifier().getId());
        throw new OperationNotAuthorizedException();
    }

    @RequestMapping(value = "/autocomplete", method = RequestMethod.GET)
    public List<String> autocomplete(@RequestParam String query, @RequestParam Integer limit, HttpSession session) {
        Person person = (Person) session.getAttribute("user");
        if (person != null)
            return dataService.autocomplete(query, person.getIdentifier().getId(), limit);
        throw new OperationNotAuthorizedException();
    }

    @RequestMapping(value = "/types", method = RequestMethod.GET)
    public List<String> types(@RequestParam String query, @RequestParam Integer limit, HttpSession session) {
        Person person = (Person) session.getAttribute("user");
        if (person != null)
            return dataService.types(query, person.getIdentifier().getId(), limit);
        throw new OperationNotAuthorizedException();
    }
}
