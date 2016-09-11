package com.cver.team.web;

import com.cver.team.model.entity.Organization;
import com.cver.team.services.OrganizationService;
import com.cver.team.web.ResponseExceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by PC on 05/09/2016.
 */
@RestController
@RequestMapping("/organization")
public class OrganizationController {
    @Autowired
    OrganizationService organizationService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Organization getOrganization(@PathVariable String id) {
        Organization organization = organizationService.getOrganization(id);
        if (organization == null)
            throw new ResourceNotFoundException();
        return organization;
    }
}
