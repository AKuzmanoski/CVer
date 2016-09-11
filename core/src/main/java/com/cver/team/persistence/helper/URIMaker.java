package com.cver.team.persistence.helper;

import com.cver.team.model.literal.Identifier;
import com.cver.team.persistence.jena.namespaces.CVR;

import java.util.UUID;

/**
 * Created by User on 2/14/2016.
 */
public class URIMaker {
    public static final String generateUri() {
        return UUID.randomUUID().toString();
    }

    public static final String generateUri(String name) {
        return UUID.fromString(name).toString();
    }

    public static final Long generateLongUri() {
        return UUID.randomUUID().node();
    }

    public static final Long generateLongUri(String name) {
        return UUID.fromString(name).node();
    }
}
