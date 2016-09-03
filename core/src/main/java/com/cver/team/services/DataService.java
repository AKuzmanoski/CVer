package com.cver.team.services;

import com.cver.team.model.data.Data;

import java.util.List;

/**
 * Created by PC on 26/08/2016.
 */
public interface DataService {
    List<Data> queryData(String query, String type, Integer offset, Integer limit, String userId);

    List<String> autocomplete(String query, Integer limit);

    List<String> types(String query, Integer limit);
}
