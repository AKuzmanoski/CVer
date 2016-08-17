package com.cver.team.persistence.jena.helper;

import org.apache.jena.datatypes.xsd.XSDDateTime;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.RDFNode;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by PC on 17/08/2016.
 */
public class DateTimeConverter {
    public static Date getDate(RDFNode node) {
        Object value = node.asLiteral().getValue();
        return ((XSDDateTime) value).asCalendar().getTime();
    }

    public static Calendar getCalendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }
}
