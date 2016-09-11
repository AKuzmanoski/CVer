package com.cver.team.persistence.jena.helper;

import com.cver.team.model.literal.Identifier;
import com.cver.team.persistence.helper.URIMaker;
import com.cver.team.persistence.jena.namespaces.CVR;

/**
 * Created by PC on 04/09/2016.
 */
public class IdentifierGenerator {
    public static final Identifier generateIdentifier() {
        Identifier identifier = new Identifier();
        String id = URIMaker.generateUri();
        identifier.setId(id);
        identifier.setURI(CVR.getURI(id));
        return identifier;
    }
}
