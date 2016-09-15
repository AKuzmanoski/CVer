package com.cver.team.persistence.jena.objectMappers.externalResourceObjectMappers;

import com.cver.team.model.externalresource.Period;
import com.cver.team.model.externalresource.Position;
import com.cver.team.persistence.jena.helper.DateTimeConverter;
import com.cver.team.persistence.jena.helper.IdentifierGenerator;
import com.cver.team.persistence.jena.namespaces.CVO;
import com.cver.team.persistence.jena.namespaces.CVR;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by PC on 25/08/2016.
 */
@Component
public class PeriodObjectMapper {
    @Autowired
    ExternalResourceObjectMapper externalResourceObjectMapper;

    public Period generatePeriod(Model model, Resource resource) {
        Period period = new Period();
        period = externalResourceObjectMapper.generateExternalResource(model, resource, period);

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

    public void createModel(Period period, Model model) {
        if (period.getIdentifier() != null)
            return;
        period.setIdentifier(IdentifierGenerator.generateIdentifier());
        Resource resource = CVR.getResource(period.getIdentifier().getId());
        createModel(period, model, resource);
    }

    private <T extends Period> void createModel(T period, Model model, Resource resource) {
        externalResourceObjectMapper.createModel(period, model, resource);

        if (period.getStartDate() != null) {
            Literal literal = model.createTypedLiteral(DateTimeConverter.getCalendar(period.getStartDate()));
            model.add(resource, CVO.getProperty("startDate"), literal);
        }

        if (period.getEndDate() != null) {
            Literal literal = model.createTypedLiteral(DateTimeConverter.getCalendar(period.getEndDate()));
            model.add(resource, CVO.getProperty("endDate"), literal);
        }
    }
}
