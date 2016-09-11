package com.cver.team.web;

import com.cver.team.persistence.jena.queries.QueryRepositoryProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by PC on 06/09/2016.
 */
@RestController
@RequestMapping("/jena")
public class JenaQueryController {
    @Autowired
    QueryRepositoryProvider queryRepositoryProvider;

    @RequestMapping(value = "/refresh/queries", method = RequestMethod.GET)
    public void refreshQueries() {
        queryRepositoryProvider.refreshQueries();
    }
}
