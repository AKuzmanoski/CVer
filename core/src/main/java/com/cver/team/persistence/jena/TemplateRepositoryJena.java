package com.cver.team.persistence.jena;

import com.cver.team.model.entity.Call;
import com.cver.team.model.entity.Entity;
import com.cver.team.model.entity.Template;
import com.cver.team.persistence.helper.LuceneUtil;
import com.cver.team.persistence.jena.namespaces.CVO;
import com.cver.team.persistence.jena.namespaces.CVR;
import com.cver.team.persistence.jena.objectMappers.entityObjectMappers.CallObjectMapper;
import com.cver.team.persistence.jena.objectMappers.entityObjectMappers.EntityObjectMapper;
import com.cver.team.persistence.jena.objectMappers.entityObjectMappers.TemplateObjectMapper;
import com.cver.team.persistence.jena.queries.Queries;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Model;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.cver.team.persistence.TemplateRepository;
import com.cver.team.persistence.jena.helper.JenaPreferences;
import com.cver.team.persistence.jena.helper.SPARQLPrefix;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dimitar on 2/28/2016.
 */
@Repository
public class TemplateRepositoryJena implements TemplateRepository {
    @Autowired
    QueryRepository queryRepository;

    @Override
    public Template getTemplateByName(String name) {
//        StringBuilder queryString = new StringBuilder();
//        queryString.append(SPARQLPrefix.cvo);
//        queryString.append("SELECT ?templateName ?format ?location ");
//        queryString.append("WHERE { ");
//        queryString.append("?template a cvo:Template ; ");
//        queryString.append("cvo:location ?location ; ");
//        queryString.append("cvo:templateName ?templateName; ");
//        queryString.append("cvo:format ?format. ");
//        queryString.append("FILTER regex(?templateName, \"" + name +"\" ) ");
//        queryString.append("}");
//        System.out.println(queryString.toString());
//        Query query = QueryFactory.createModel(queryString.toString());
//        try (QueryExecution queryExecution = QueryExecutionFactory.sparqlService(JenaPreferences.SPARQLEndpoint, query)) {
//            ResultSet resultSet = queryExecution.execSelect();
//            if (resultSet.hasNext()) {
//                QuerySolution querySolution = resultSet.nextSolution();
//                Template template = new Template();
//                template.setName(querySolution.get("templateName").toString());
//                template.setFormat(querySolution.get("format").toString());
//                template.setLocation(querySolution.get("location").toString());
//                return template;
//            }
//        }
        return null;
    }

    @Override
    public Template getTemplate(String id) {
        String uri = CVR.getURI(id);
        ParameterizedSparqlString queryString = new ParameterizedSparqlString();
        queryString.setCommandText(queryRepository.getQuery(Queries.getTemplate));
        queryString.setIri("template", uri);

        Query query = queryString.asQuery();
        QueryExecution queryExecution = QueryExecutionFactory.sparqlService(JenaPreferences.SPARQLEndpoint, query);
        Model model = queryExecution.execConstruct();
        model.write(System.out, "TURTLE");
        Template template = TemplateObjectMapper.generateTemplate(model, uri);
        return template;
    }

    @Override
    public List<Template> queryTemplates(String query, String type, Integer offset, Integer limit, String userId) {
        query = LuceneUtil.analyzeString(new StandardAnalyzer(), query);
        if (type != null) {
            query += " AND type:\"" + CVO.getURI(type) + "\"";
        }
        query += " AND (public:true";
        if (userId != null) {
            query += " OR owner:\"" + CVR.getURI(userId) + "\"";
        }
        query += ")";
        ParameterizedSparqlString queryString = new ParameterizedSparqlString();
        queryString.setCommandText(queryRepository.getQuery(Queries.template_query));
        queryString.setLiteral("query", query);
        queryString.setLiteral("offset", offset.toString());
        queryString.setLiteral("limit", limit.toString());

        //System.out.println(new Date().toString());
        Query myQuery = queryString.asQuery();
        QueryExecution queryExecution = QueryExecutionFactory.sparqlService(JenaPreferences.SPARQLEndpoint, myQuery);
        Model model = queryExecution.execConstruct();
        //System.out.println(new Date().toString());
        List<Template> entities = TemplateObjectMapper.generateTemplates(model);
        //System.out.println(new Date().toString());
        return entities;
    }

    @Override
    public List<Template> queryCvTemplates(String query, String type, Integer offset, Integer limit, String userId) {
        query = LuceneUtil.analyzeString(new StandardAnalyzer(), query);
        query += " AND type:\"" + CVO.getURI("CVTemplate") + "\"";
        if (type != null) {
            query += " AND type:\"" + CVO.getURI(type) + "\"";
        }
        query += " AND (public:true";
        if (userId != null) {
            query += " OR owner:\"" + CVR.getURI(userId) + "\"";
        }
        query += ")";
        ParameterizedSparqlString queryString = new ParameterizedSparqlString();
        queryString.setCommandText(queryRepository.getQuery(Queries.template_query));
        queryString.setLiteral("query", query);
        queryString.setLiteral("offset", offset.toString());
        queryString.setLiteral("limit", limit.toString());

        //System.out.println(new Date().toString());
        Query myQuery = queryString.asQuery();
        QueryExecution queryExecution = QueryExecutionFactory.sparqlService(JenaPreferences.SPARQLEndpoint, myQuery);
        Model model = queryExecution.execConstruct();
        //System.out.println(new Date().toString());
        List<Template> entities = TemplateObjectMapper.generateTemplates(model);
        //System.out.println(new Date().toString());
        return entities;
    }

    @Override
    public List<Template> queryCertificateTemplates(String query, String type, Integer offset, Integer limit, String userId) {
        query = LuceneUtil.analyzeString(new StandardAnalyzer(), query);
        query += " AND type:\"" + CVO.getURI("CertificateTemplate") + "\"";
        if (type != null) {
            query += " AND type:\"" + CVO.getURI(type) + "\"";
        }
        query += " AND (public:true";
        if (userId != null) {
            query += " OR owner:\"" + CVR.getURI(userId) + "\"";
        }
        query += ")";

        ParameterizedSparqlString queryString = new ParameterizedSparqlString();
        queryString.setCommandText(queryRepository.getQuery(Queries.template_query));
        queryString.setLiteral("query", query);
        queryString.setLiteral("offset", offset.toString());
        queryString.setLiteral("limit", limit.toString());

        //System.out.println(new Date().toString());
        Query myQuery = queryString.asQuery();
        QueryExecution queryExecution = QueryExecutionFactory.sparqlService(JenaPreferences.SPARQLEndpoint, myQuery);
        Model model = queryExecution.execConstruct();
        //System.out.println(new Date().toString());
        List<Template> entities = TemplateObjectMapper.generateTemplates(model);
        //System.out.println(new Date().toString());
        return entities;
    }

    @Override
    public List<String> autocompleteCertificate(String query, String userId, Integer limit) {
        query = "(" + LuceneUtil.analyzeString(new StandardAnalyzer(), query) + ")";
        query += " AND type:\"" + CVO.getURI("CertificateTemplate") + "\"";
        query += " AND (public:true";
        if (userId != null) {
            query += " OR owner:\"" + CVR.getURI(userId) + "\"";
        }
        query += ")";
        List<String> list = new ArrayList<>();
        ParameterizedSparqlString queryString = new ParameterizedSparqlString();
        queryString.setCommandText(queryRepository.getQuery(Queries.template_autocomplete));
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
    public List<String> typesCertificate(String query, String userId, Integer limit) {
        query = "(" + LuceneUtil.analyzeString(new StandardAnalyzer(), query) + ")";
        query += " AND type:\"" + CVO.getURI("CertificateTemplate") + "\"";
        query += " AND (public:true";
        if (userId != null) {
            query += " OR owner:\"" + CVR.getURI(userId) + "\"";
        }
        query += ")";
        List<String> list = new ArrayList<>();
        ParameterizedSparqlString queryString = new ParameterizedSparqlString();
        queryString.setCommandText(queryRepository.getQuery(Queries.template_type_query));
        queryString.setLiteral("query", query);
        queryString.setLiteral("limit", limit);
        Query myQuery = queryString.asQuery();
        QueryExecution queryExecution = QueryExecutionFactory.sparqlService(JenaPreferences.SPARQLEndpoint, myQuery);
        ResultSet resultSet = queryExecution.execSelect();
        while (resultSet.hasNext()) {
            QuerySolution querySolution = resultSet.next();
            list.add(CVO.getId(querySolution.get("type").toString())    );
        }
        return list;
    }

    @Override
    public List<String> autocompleteCV(String query, String userId, Integer limit) {
        query = "(" + LuceneUtil.analyzeString(new StandardAnalyzer(), query) + ")";
        query += " AND type:\"" + CVO.getURI("CVTemplate") + "\"";
        query += " AND (public:true";
        if (userId != null) {
            query += " OR owner:\"" + CVR.getURI(userId) + "\"";
        }
        query += ")";
        List<String> list = new ArrayList<>();
        ParameterizedSparqlString queryString = new ParameterizedSparqlString();
        queryString.setCommandText(queryRepository.getQuery(Queries.template_autocomplete));
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
    public List<String> typesCV(String query, String userId, Integer limit) {
        query = "(" + LuceneUtil.analyzeString(new StandardAnalyzer(), query) + ")";
        query += " AND type:\"" + CVO.getURI("CVTemplate") + "\"";
        query += " AND (public:true";
        if (userId != null) {
            query += " OR owner:\"" + CVR.getURI(userId) + "\"";
        }
        query += ")";
        List<String> list = new ArrayList<>();
        ParameterizedSparqlString queryString = new ParameterizedSparqlString();
        queryString.setCommandText(queryRepository.getQuery(Queries.template_type_query));
        queryString.setLiteral("query", query);
        queryString.setLiteral("limit", limit);
        Query myQuery = queryString.asQuery();
        QueryExecution queryExecution = QueryExecutionFactory.sparqlService(JenaPreferences.SPARQLEndpoint, myQuery);
        ResultSet resultSet = queryExecution.execSelect();
        while (resultSet.hasNext()) {
            QuerySolution querySolution = resultSet.next();
            list.add(CVO.getId(querySolution.get("type").toString())    );
        }
        return list;
    }

    @Override
    public List<String> autocomplete(String query, String userId, Integer limit) {
        query = "(" + LuceneUtil.analyzeString(new StandardAnalyzer(), query) + ")";
        query += " AND (public:true";
        if (userId != null) {
            query += " OR owner:\"" + CVR.getURI(userId) + "\"";
        }
        query += ")";
        List<String> list = new ArrayList<>();
        ParameterizedSparqlString queryString = new ParameterizedSparqlString();
        queryString.setCommandText(queryRepository.getQuery(Queries.template_autocomplete));
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
        query += " AND (public:true";
        if (userId != null) {
            query += " OR owner:\"" + CVR.getURI(userId) + "\"";
        }
        query += ")";
        List<String> list = new ArrayList<>();
        ParameterizedSparqlString queryString = new ParameterizedSparqlString();
        queryString.setCommandText(queryRepository.getQuery(Queries.template_type_query));
        queryString.setLiteral("query", query);
        queryString.setLiteral("limit", limit);
        Query myQuery = queryString.asQuery();
        QueryExecution queryExecution = QueryExecutionFactory.sparqlService(JenaPreferences.SPARQLEndpoint, myQuery);
        ResultSet resultSet = queryExecution.execSelect();
        while (resultSet.hasNext()) {
            QuerySolution querySolution = resultSet.next();
            list.add(CVO.getId(querySolution.get("type").toString())    );
        }
        return list;
    }
}
