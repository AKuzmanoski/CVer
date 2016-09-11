package com.cver.team.persistence;

import com.cver.team.model.entity.Organization;

/**
 * Created by PC on 05/09/2016.
 */
public interface OrganizationRepository {
    Organization getOrganization(String id);
}
