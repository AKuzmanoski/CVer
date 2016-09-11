package com.cver.team.persistence.jena.objectMappers.dataObjectMappers.stringObjectMappers;

import com.cver.team.model.data.string.LastName;
import com.cver.team.model.data.string.TelephoneNumber;
import com.cver.team.persistence.jena.helper.IdentifierGenerator;
import com.cver.team.persistence.jena.namespaces.CVR;
import com.cver.team.persistence.jena.objectMappers.dataObjectMappers.DataObjectMapper;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.springframework.stereotype.Component;

/**
 * Created by PC on 25/08/2016.
 */
@Component
public class TelephoneNumberObjectMapper {
    public TelephoneNumber generateTelephoneNumber(Model model, Resource resource) {
        TelephoneNumber telephoneNumber = new TelephoneNumber();
        telephoneNumber = DataObjectMapper.generateData(model, resource, telephoneNumber);
        return telephoneNumber;
    }

    public void createModel(TelephoneNumber telephoneNumber, Model model) {
        if (telephoneNumber.getIdentifier() != null)
            return;
        telephoneNumber.setIdentifier(IdentifierGenerator.generateIdentifier());
        Resource resource = CVR.getResource(telephoneNumber.getIdentifier().getId());
        createModel(telephoneNumber, model, resource);
    }

    public <T extends TelephoneNumber> void createModel(T telephoneNumber, Model model, Resource resource) {
        DataObjectMapper.createModel(telephoneNumber, model, resource);
    }
}
