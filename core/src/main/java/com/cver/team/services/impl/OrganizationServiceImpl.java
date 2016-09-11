package com.cver.team.services.impl;

import com.cver.team.model.entity.Organization;
import com.cver.team.persistence.OrganizationRepository;
import com.cver.team.services.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by PC on 05/09/2016.
 */
@Service
public class OrganizationServiceImpl implements OrganizationService {
    @Autowired
    OrganizationRepository organizationRepository;

    @Override
    public Organization getOrganization(String id) {
        return organizationRepository.getOrganization(id);
    }
}
