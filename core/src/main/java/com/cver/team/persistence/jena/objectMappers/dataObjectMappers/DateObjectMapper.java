package com.cver.team.persistence.jena.objectMappers.dataObjectMappers;

import com.cver.team.model.data.Date;
import com.cver.team.persistence.jena.helper.DateTimeConverter;
import com.cver.team.persistence.jena.namespaces.CVO;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;

/**
 * Created by PC on 25/08/2016.
 */
public class DateObjectMapper {
    public static <T extends Date> T generateDate(Model model, Resource resource, T date) {
        date = DataObjectMapper.generateData(model, resource, date);

        Statement statement = resource.getProperty(CVO.getProperty("val"));
        if (statement != null) {
            date.setDateTime(DateTimeConverter.getDate(statement.getObject()));
        }

        return date;
    }
}
