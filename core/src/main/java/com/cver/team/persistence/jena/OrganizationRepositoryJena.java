package com.cver.team.persistence.jena;

import com.cver.team.model.entity.Call;
import com.cver.team.model.entity.Organization;
import com.cver.team.persistence.OrganizationRepository;
import com.cver.team.persistence.jena.helper.JenaPreferences;
import com.cver.team.persistence.jena.namespaces.CVR;
import com.cver.team.persistence.jena.objectMappers.entityObjectMappers.CallObjectMapper;
import com.cver.team.persistence.jena.objectMappers.entityObjectMappers.OrganizationObjectMapper;
import com.cver.team.persistence.jena.queries.Queries;
import org.apache.jena.query.ParameterizedSparqlString;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.rdf.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by PC on 05/09/2016.
 */
@Repository
public class OrganizationRepositoryJena implements OrganizationRepository {
    @Autowired
    QueryRepository queryRepository;
    @Autowired
    OrganizationObjectMapper organizationObjectMapper;

    @Override
    public Organization getOrganization(String id) {
        String uri = CVR.getURI(id);
        ParameterizedSparqlString queryString = new ParameterizedSparqlString();
        queryString.setCommandText(queryRepository.getQuery(Queries.getOrganization));
        queryString.setIri("organization", CVR.getURI(id));

        Query query = queryString.asQuery();
        QueryExecution queryExecution = QueryExecutionFactory.sparqlService(JenaPreferences.SPARQLEndpoint, query);
        Model model = queryExecution.execConstruct();
        Organization organization = organizationObjectMapper.generateOrganization(model, uri);
        return organization;
    }
}
