package com.cver.team.persistence.jena;

import com.cver.team.model.data.Data;
import com.cver.team.model.entity.Entity;
import com.cver.team.persistence.DataRepository;
import com.cver.team.persistence.jena.helper.JenaPreferences;
import com.cver.team.persistence.jena.namespaces.CVR;
import com.cver.team.persistence.jena.objectMappers.dataObjectMappers.DataObjectMapper;
import com.cver.team.persistence.jena.objectMappers.entityObjectMappers.EntityObjectMapper;
import com.cver.team.persistence.jena.queries.Queries;
import org.apache.jena.query.ParameterizedSparqlString;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.rdf.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by PC on 26/08/2016.
 */
@Repository
public class DataRepositoryJena implements DataRepository {
    @Autowired
    QueryRepository queryRepository;

    @Override
    public List<Data> queryData(String query, String type, Integer offset, Integer limit, String userId) {
        query += " AND usedBy:\"" + CVR.getURI(userId) + "\"";
        ParameterizedSparqlString queryString = new ParameterizedSparqlString();
        queryString.setCommandText(queryRepository.getQuery(Queries.data_query));
        queryString.setLiteral("query", query);
        queryString.setLiteral("offset", offset.toString());
        queryString.setLiteral("limit", limit.toString());

        //System.out.println(new Date().toString());
        Query myQuery = queryString.asQuery();
        QueryExecution queryExecution = QueryExecutionFactory.sparqlService(JenaPreferences.SPARQLEndpoint, myQuery);
        Model model = queryExecution.execConstruct();
        //System.out.println(new Date().toString());
        List<Data> entities = DataObjectMapper.generateDataP(model);
        //System.out.println(new Date().toString());
        return entities;
    }
}
