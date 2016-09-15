package com.cver.team.persistence.jena.objectMappers.entityObjectMappers;

import com.cver.team.model.entity.CvCard;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by PC on 17/08/2016.
 */
@Component
public class CvCardObjectMapper {
    @Autowired
    DocumentCardObjectMapper documentCardObjectMapper;

    public CvCard generateCvCard(Model model, Resource resource) {
        CvCard cvCard = new CvCard();

        cvCard = documentCardObjectMapper.generateDocument(model, resource, cvCard);

        return cvCard;
    }
}
