package com.cver.team.persistence.jena.objectMappers.externalResourceObjectMappers;

import com.cver.team.model.externalresource.Period;
import com.cver.team.persistence.jena.helper.DateTimeConverter;
import com.cver.team.persistence.jena.namespaces.CVO;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;

/**
 * Created by PC on 25/08/2016.
 */
public class PeriodObjectMapper {
    public static Period generatePeriod(Model model, Resource resource) {
        Period period = new Period();
        period = ExternalResourceObjectMapper.generateExternalResource(model, resource, period);

        // Start date
        Statement statement = resource.getProperty(CVO.getProperty("startDate"));
        if (statement != null) {
            period.setStartDate(DateTimeConverter.getDate(statement.getObject()));
        }

        // End date
        statement = resource.getProperty(CVO.getProperty("endDate"));
        if (statement != null) {
            period.setEndDate(DateTimeConverter.getDate(statement.getObject()));
        }

        return period;
    }
}
