package com.cver.team.services;

import com.cver.team.model.externalresource.StaticFile;

/**
 * Created by PC on 09/09/2016.
 */
public interface StaticFileService {
    StaticFile get(String id);

    StaticFile save(StaticFile staticFile);
}
