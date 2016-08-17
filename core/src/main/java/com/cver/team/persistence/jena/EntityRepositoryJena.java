package com.cver.team.persistence.jena;

import com.cver.team.model.entity.Entity;
import com.cver.team.persistence.EntityRepository;
import com.cver.team.persistence.jena.helper.JenaPreferences;
import com.cver.team.persistence.jena.objectMappers.EntityObjectMapper;
import com.cver.team.persistence.jena.queries.Queries;
import org.apache.jena.datatypes.xsd.XSDDatatype;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PC on 16/08/2016.
 */
@Repository
public class EntityRepositoryJena implements EntityRepository {
    @Autowired
    QueryRepository queryRepository;

    @Override
    public List<Entity> query(String query, String type, Integer offset, Integer limit) {
        if (type != null) {
            query = query + " typeOfEntity:" + type;
        }
        ParameterizedSparqlString queryString = new ParameterizedSparqlString();
        queryString.setCommandText(queryRepository.getQuery(Queries.entity_query));
        queryString.setLiteral("query", query);
        queryString.setLiteral("offset", offset.toString());
        queryString.setLiteral("limit", limit.toString());

        Query myQuery = queryString.asQuery();
        QueryExecution queryExecution = QueryExecutionFactory.sparqlService(JenaPreferences.SPARQLEndpoint, myQuery);
        Model model = queryExecution.execConstruct();
        return EntityObjectMapper.generateEntities(model);
    }

    @Override
    public List<String> autocomplete(String query, Integer offset, Integer limit) {
        List<String> list = new ArrayList<>();
        ParameterizedSparqlString queryString = new ParameterizedSparqlString();
        queryString.setCommandText(queryRepository.getQuery(Queries.entity_autocomplete));
        queryString.setLiteral("query", query);
        queryString.setLiteral("offset", offset.toString());
        queryString.setLiteral("limit", limit.toString());
        Query myQuery = queryString.asQuery();
        System.out.println(queryString.toString());
        QueryExecution queryExecution = QueryExecutionFactory.sparqlService(JenaPreferences.SPARQLEndpoint, myQuery);
        ResultSet resultSet = queryExecution.execSelect();
        while (resultSet.hasNext()) {
            QuerySolution querySolution = resultSet.next();
            list.add(querySolution.get("snippetText").toString());
        }
        return list;
    }
}
