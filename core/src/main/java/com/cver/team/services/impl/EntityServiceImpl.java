package com.cver.team.services.impl;

import com.cver.team.model.entity.Entity;
import com.cver.team.persistence.EntityRepository;
import com.cver.team.services.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by PC on 16/08/2016.
 */
@Service
public class EntityServiceImpl implements EntityService {
    @Autowired
    EntityRepository entityRepository;

    @Override
    public List<Entity> query(String query, String type, String owner, String memberOf, String isWatchedBy, String owns, Integer offset, Integer limit) {
        return entityRepository.query(query, type, owner, memberOf, isWatchedBy, owns, offset, limit);
    }

    @Override
    public List<String> autocomplete(String query, String type, String owner, String memberOf, String isWatchedBy, String owns, Integer limit) {
        return entityRepository.autocomplete(query, type, owner, memberOf, isWatchedBy, owns, limit);
    }

    @Override
    public List<String> types(String query, String type, String owner, String memberOf, String isWatchedBy, String owns, Integer limit) {
        return entityRepository.types(query, type, owner, memberOf, isWatchedBy, owns, limit);
    }
}
