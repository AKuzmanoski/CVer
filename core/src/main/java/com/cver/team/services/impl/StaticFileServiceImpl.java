package com.cver.team.services.impl;

import com.cver.team.model.externalresource.StaticFile;
import com.cver.team.persistence.StaticFileRepository;
import com.cver.team.services.StaticFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by PC on 10/09/2016.
 */
@Service
public class StaticFileServiceImpl implements StaticFileService {
    @Autowired
    StaticFileRepository staticFileRepository;

    @Override
    public StaticFile get(String id) {
        return staticFileRepository.get(id);
    }

    @Override
    public StaticFile save(StaticFile staticFile) {
        return staticFileRepository.save(staticFile);
    }
}
