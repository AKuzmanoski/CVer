package com.cver.team.persistence;

import com.cver.team.model.externalresource.StaticFile;

/**
 * Created by PC on 10/09/2016.
 */
public interface StaticFileRepository {
    StaticFile get(String id);

    StaticFile save(StaticFile staticFile);
}
