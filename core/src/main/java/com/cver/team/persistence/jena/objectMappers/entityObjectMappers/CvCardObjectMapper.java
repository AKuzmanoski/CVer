package com.cver.team.persistence.jena.objectMappers.entityObjectMappers;

import com.cver.team.model.entity.CvCard;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;

/**
 * Created by PC on 17/08/2016.
 */
public class CvCardObjectMapper {
    public static CvCard generateCvCard(Model model, Resource resource) {
        CvCard cvCard = new CvCard();

        cvCard = DocumentCardObjectMapper.generateDocument(model, resource, cvCard);

        return cvCard;
    }
}
