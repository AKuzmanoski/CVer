package com.cver.team.web;

import com.cver.team.model.data.Data;
import com.cver.team.model.entity.Person;
import com.cver.team.services.DataService;
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
        Person person = (Person)session.getAttribute("user");
        return dataService.queryData(query, type, offset, limit, person.getIdentifier().getId());
    }


}
