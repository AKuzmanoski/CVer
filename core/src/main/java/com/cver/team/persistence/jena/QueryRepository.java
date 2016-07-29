package com.cver.team.persistence.jena;

import com.cver.team.persistence.jena.queries.Queries;

/**
 * Created by User on 7/28/2016.
 */
public interface QueryRepository {
    String getRemoteQuery(String name);

    String getQuery(String name);

    String getRemoteQuery(Queries name);

    String getQuery(Queries name);
}
