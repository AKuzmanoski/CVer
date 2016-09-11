package com.cver.team.persistence.jena;

import com.cver.team.model.entity.Entity;
import com.cver.team.persistence.EntityRepository;
import com.cver.team.persistence.helper.LuceneUtil;
import com.cver.team.persistence.jena.helper.JenaPreferences;
import com.cver.team.persistence.jena.namespaces.CVO;
import com.cver.team.persistence.jena.namespaces.CVR;
import com.cver.team.persistence.jena.objectMappers.entityObjectMappers.EntityObjectMapper;
import com.cver.team.persistence.jena.queries.Queries;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Model;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
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
    public List<Entity> query(String query, String type, String owner, Integer offset, Integer limit) {
        query = "(" + LuceneUtil.analyzeString(new StandardAnalyzer(), query) + ")";
        if (type != null) {
            query += " AND type:\"" + CVO.getURI(type) + "\"";
        }
        if (owner != null) {
            query += " AND owner:\"" + CVR.getURI(owner) + "\"";
        }
        ParameterizedSparqlString queryString = new ParameterizedSparqlString();
        queryString.setCommandText(queryRepository.getQuery(Queries.entity_query));
        queryString.setLiteral("query", query);
        queryString.setLiteral("offset", offset.toString());
        queryString.setLiteral("limit", limit.toString());

        //System.out.println(new Date().toString());
        Query myQuery = queryString.asQuery();
        QueryExecution queryExecution = QueryExecutionFactory.sparqlService(JenaPreferences.SPARQLEndpoint, myQuery);
        Model model = queryExecution.execConstruct();
        List<Entity> entities = EntityObjectMapper.generateEntities(model);
        return entities;
    }

    @Override
    public List<String> autocomplete(String query, String type, String owner, Integer limit) {
        query = "(" + LuceneUtil.analyzeString(new StandardAnalyzer(), query) + ")";
        if (owner != null) {
            query += " AND owner:\"" + CVR.getURI(owner) + "\"";
        }
        if (type != null) {
            query += " AND type:\"" + CVO.getURI(type) + "\"";
        }
        List<String> list = new ArrayList<>();
        ParameterizedSparqlString queryString = new ParameterizedSparqlString();
        queryString.setCommandText(queryRepository.getQuery(Queries.entity_autocomplete));
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
    public List<String> types(String query, String type, String owner, Integer limit) {
        query = "(" + LuceneUtil.analyzeString(new StandardAnalyzer(), query) + ")";
        if (owner != null) {
            query += " AND owner:\"" + CVR.getURI(owner) + "\"";
        }
        if (type != null) {
            query += " AND type:\"" + CVO.getURI(type) + "\"";
        }
        List<String> list = new ArrayList<>();
        ParameterizedSparqlString queryString = new ParameterizedSparqlString();
        queryString.setCommandText(queryRepository.getQuery(Queries.entity_type_query));
        queryString.setLiteral("query", query);
        queryString.setLiteral("limit", limit);
        if (type != null)
            queryString.setIri("mainType", CVO.getURI(type));
        else queryString.setIri("mainType", CVO.getURI("Entity"));
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
