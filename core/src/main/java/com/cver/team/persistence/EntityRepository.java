package com.cver.team.persistence;

import com.cver.team.model.entity.Entity;

import java.util.List;

/**
 * Created by PC on 16/08/2016.
 */
public interface EntityRepository {
    List<Entity> query(String query, String type, Integer offset, Integer limit);

    List<String> autocomplete(String query,  Integer offset, Integer limit);
}
