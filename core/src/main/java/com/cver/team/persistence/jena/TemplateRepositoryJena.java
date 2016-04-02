package com.cver.team.persistence.jena;

import com.cver.team.model.Template;
import org.apache.jena.query.*;
import org.springframework.stereotype.Repository;
import com.cver.team.persistence.TemplateRepository;
import com.cver.team.persistence.jena.helper.JenaPreferences;
import com.cver.team.persistence.jena.helper.SPARQLPrefix;

/**
 * Created by Dimitar on 2/28/2016.
 */
@Repository
public class TemplateRepositoryJena implements TemplateRepository {
    @Override
    public Template getTemplateByName(String name) {
        StringBuilder queryString = new StringBuilder();
        queryString.append(SPARQLPrefix.cvo);
        queryString.append("SELECT ?templateName ?format ?location ");
        queryString.append("WHERE { ");
        queryString.append("?template a cvo:Template ; ");
        queryString.append("cvo:location ?location ; ");
        queryString.append("cvo:templateName ?templateName; ");
        queryString.append("cvo:format ?format. ");
        queryString.append("FILTER regex(?templateName, \"" + name +"\" ) ");
        queryString.append("}");
        System.out.println(queryString.toString());
        Query query = QueryFactory.create(queryString.toString());
        try (QueryExecution queryExecution = QueryExecutionFactory.sparqlService(JenaPreferences.SPARQLEndpoint, query)) {
            ResultSet resultSet = queryExecution.execSelect();
            if (resultSet.hasNext()) {
                QuerySolution querySolution = resultSet.nextSolution();
                Template template = new Template();
                template.setName(querySolution.get("templateName").toString());
                template.setFormat(querySolution.get("format").toString());
                template.setLocation(querySolution.get("location").toString());
                return template;
            }
        }
        return null;
    }
}
