package com.cver.team.services.impl;

import com.cver.team.model.data.Data;
import com.cver.team.persistence.DataRepository;
import com.cver.team.services.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by PC on 26/08/2016.
 */
@Service
public class DataServiceImpl implements DataService {
    @Autowired
    DataRepository dataRepository;

    @Override
    public List<Data> queryData(String query, String type, Integer offset, Integer limit, String userId) {
        return dataRepository.queryData(query, type, offset, limit, userId);
    }

    @Override
    public List<String> autocomplete(String query, String userId, Integer limit) {
        return dataRepository.autocomplete(query, userId, limit);
    }

    @Override
    public List<String> types(String query, String userId, Integer limit) {
        return dataRepository.types(query, userId, limit);
    }
}
