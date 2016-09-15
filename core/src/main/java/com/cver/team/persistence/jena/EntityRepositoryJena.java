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
    @Autowired
    EntityObjectMapper entityObjectMapper;

    @Override
    public List<Entity> query(String query, String type, String owner, String memberOf, String isWatchedBy, String owns, Integer offset, Integer limit) {
        query = "(" + LuceneUtil.analyzeString(new StandardAnalyzer(), query) + ")";
        if (type != null) {
            query += " AND type:\"" + CVO.getURI(type) + "\"";
        }
        if (owner != null) {
            query += " AND owner:\"" + CVR.getURI(owner) + "\"";
        }
        if (memberOf != null) {
            query += " AND memberOf:\"" + CVR.getURI(memberOf) + "\"";
        }
        if (isWatchedBy != null) {
            query += " AND isWatchedBy:\"" + CVR.getURI(isWatchedBy) + "\"";
        }
        if (owns != null) {
            query += " AND owns:\"" + CVR.getURI(owns) + "\"";
        }
        ParameterizedSparqlString queryString = new ParameterizedSparqlString();
        queryString.setCommandText(queryRepository.getQuery(Queries.entity_query));
        queryString.setLiteral("query", query);
        queryString.setLiteral("offset", offset.toString());
        queryString.setLiteral("limit", limit.toString());


        System.out.println(queryString.toString());
        Query myQuery = queryString.asQuery();
        QueryExecution queryExecution = QueryExecutionFactory.sparqlService(JenaPreferences.SPARQLEndpoint, myQuery);
        Model model = queryExecution.execConstruct();
        List<Entity> entities = entityObjectMapper.generateEntities(model);
        return entities;
    }

    @Override
    public List<String> autocomplete(String query, String type, String owner, String memberOf, String isWatchedBy, String owns, Integer limit) {
        query = "(" + LuceneUtil.analyzeString(new StandardAnalyzer(), query) + ")";
        if (owner != null) {
            query += " AND owner:\"" + CVR.getURI(owner) + "\"";
        }
        if (type != null) {
            query += " AND type:\"" + CVO.getURI(type) + "\"";
        }
        if (memberOf != null) {
            query += " AND memberOf:\"" + CVR.getURI(memberOf) + "\"";
        }
        if (isWatchedBy != null) {
            query += " AND isWatchedBy:\"" + CVR.getURI(isWatchedBy) + "\"";
        }
        if (owns != null) {
            query += " AND owns:\"" + CVR.getURI(owns) + "\"";
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
    public List<String> types(String query, String type, String owner, String memberOf, String isWatchedBy, String owns, Integer limit) {
        query = "(" + LuceneUtil.analyzeString(new StandardAnalyzer(), query) + ")";
        if (owner != null) {
            query += " AND owner:\"" + CVR.getURI(owner) + "\"";
        }
        if (type != null) {
            query += " AND type:\"" + CVO.getURI(type) + "\"";
        }
        if (memberOf != null) {
            query += " AND memberOf:\"" + CVR.getURI(memberOf) + "\"";
        }
        if (isWatchedBy != null) {
            query += " AND isWatchedBy:\"" + CVR.getURI(isWatchedBy) + "\"";
        }
        if (owns != null) {
            query += " AND owns:\"" + CVR.getURI(owns) + "\"";
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
