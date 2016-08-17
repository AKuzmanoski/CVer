package com.cver.team.web;

import com.cver.team.model.entity.Entity;
import com.cver.team.services.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by PC on 16/08/2016.
 */

@RestController
@RequestMapping("/entity")
public class EntityController {
    @Autowired
    EntityService entityService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Entity> query(@RequestParam String query, @RequestParam(required = false) String type, @RequestParam Integer offset, @RequestParam Integer limit) {
        return entityService.query(query, type, offset, limit);
    }

    @RequestMapping(value = "/autocomplete", method = RequestMethod.GET)
    public List<String> autocomplete(@RequestParam String query, @RequestParam Integer offset, @RequestParam Integer limit) {
        return entityService.autocomplete(query, offset, limit);
    }
}
