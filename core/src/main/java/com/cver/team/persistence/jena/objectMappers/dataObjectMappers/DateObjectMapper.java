package com.cver.team.persistence.jena.objectMappers.dataObjectMappers;

import com.cver.team.model.data.Date;
import com.cver.team.model.data.date.DateOfBirth;
import com.cver.team.persistence.jena.helper.DateTimeConverter;
import com.cver.team.persistence.jena.namespaces.CVO;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.impl.LiteralImpl;
import org.apache.jena.rdf.model.impl.StatementImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by PC on 25/08/2016.
 */
@Component
public class DateObjectMapper {
    @Autowired
    DataObjectMapper dataObjectMapper;

    public <T extends Date> T generateDate(Model model, Resource resource, T date) {
        date = dataObjectMapper.generateData(model, resource, date);

        Statement statement = resource.getProperty(CVO.getProperty("val"));
        if (statement != null) {
            date.setDateTime(DateTimeConverter.getDate(statement.getObject()));
        }

        return date;
    }

    public <T extends Date> void createModel(T date, Model model, Resource resource) {
        dataObjectMapper.createModel(date, model, resource);
        if (date.getDateTime() != null) {
            Literal literal = model.createTypedLiteral(DateTimeConverter.getCalendar(date.getDateTime()));
            model.add(new StatementImpl(resource, CVO.getProperty("val"), literal));
        }
    }
}
