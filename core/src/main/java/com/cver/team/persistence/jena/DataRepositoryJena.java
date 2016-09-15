package com.cver.team.persistence.jena;

import com.cver.team.model.data.Data;
import com.cver.team.persistence.DataRepository;
import com.cver.team.persistence.helper.LuceneUtil;
import com.cver.team.persistence.jena.helper.JenaPreferences;
import com.cver.team.persistence.jena.namespaces.CVO;
import com.cver.team.persistence.jena.namespaces.CVR;
import com.cver.team.persistence.jena.objectMappers.dataObjectMappers.DataObjectMapper;
import com.cver.team.persistence.jena.queries.Queries;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Model;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PC on 26/08/2016.
 */
@Repository
public class DataRepositoryJena implements DataRepository {
    @Autowired
    QueryRepository queryRepository;
    @Autowired
    DataObjectMapper dataObjectMapper;

    @Override
    public List<Data> queryData(String query, String type, Integer offset, Integer limit, String userId) {
        query = "(" + LuceneUtil.analyzeString(new StandardAnalyzer(), query) + ")";
        query += " AND usedBy:\"" + CVR.getURI(userId) + "\"";
        if (type != null)
            query += " AND type:\"" + CVO.getURI(type) + "\"";
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
        List<Data> entities = dataObjectMapper.generateDataP(model);
        //System.out.println(new Date().toString());
        return entities;
    }

    @Override
    public List<String> autocomplete(String query, String userId, Integer limit) {
        query = "(" + LuceneUtil.analyzeString(new StandardAnalyzer(), query) + ")";
        query += " AND usedBy:\"" + CVR.getURI(userId) + "\"";
        List<String> list = new ArrayList<>();
        ParameterizedSparqlString queryString = new ParameterizedSparqlString();
        queryString.setCommandText(queryRepository.getQuery(Queries.data_autocomplete));
        queryString.setLiteral("query", query);
        queryString.setLiteral("limit", limit);
        Query myQuery = queryString.asQuery();
        QueryExecution queryExecution = QueryExecutionFactory.sparqlService(JenaPreferences.SPARQLEndpoint, myQuery);
        ResultSet resultSet = queryExecution.execSelect();
        while (resultSet.hasNext()) {
            QuerySolution querySolution = resultSet.next();
            list.add(querySolution.get("snippetText").toString());
        }
        return list;
    }

    @Override
    public List<String> types(String query, String userId, Integer limit) {
        query = "(" + LuceneUtil.analyzeString(new StandardAnalyzer(), query) + ")";
        query += " AND usedBy:\"" + CVR.getURI(userId) + "\"";
        List<String> list = new ArrayList<>();
        ParameterizedSparqlString queryString = new ParameterizedSparqlString();
        queryString.setCommandText(queryRepository.getQuery(Queries.data_type_query));
        queryString.setLiteral("query", query);
        queryString.setLiteral("limit", limit);
        Query myQuery = queryString.asQuery();
        QueryExecution queryExecution = QueryExecutionFactory.sparqlService(JenaPreferences.SPARQLEndpoint, myQuery);
        ResultSet resultSet = queryExecution.execSelect();
        while (resultSet.hasNext()) {
            QuerySolution querySolution = resultSet.next();
            list.add(CVO.getId(querySolution.get("type").toString()));
        }
        return list;
    }
}