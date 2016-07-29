package com.cver.team.persistence.jena.queries;

import com.cver.team.persistence.jena.QueryRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by User on 7/28/2016.
 */
@Repository
public class QueryRepositoryJena implements QueryRepository, QueryRepositoryProvider {
    private Map<String, String> queries;

    @PostConstruct
    public void refreshQueries() {
        queries = new HashMap<>();
        RestTemplate restTemplate = new RestTemplate();
        GraphQuery[] q = restTemplate.getForObject("http://localhost:7200//rest/sparql/saved-queries", GraphQuery[].class);
        for (GraphQuery query : q) {
            queries.put(query.getName(), query.getBody());
        }
    }

    public String getQuery(String name) {
        return queries.get(name);
    }

    public String getRemoteQuery(String name) {
        RestTemplate restTemplate = new RestTemplate();
        GraphQuery q = restTemplate.getForObject("http://localhost:7200//rest/sparql/saved-queries/" + name, GraphQuery.class);
        return q.body;
    }

    @Override
    public String getRemoteQuery(Queries name) {
        return getRemoteQuery(name.name());
    }

    @Override
    public String getQuery(Queries name) {
        return getQuery(name.name());
    }
}
