package com.cver.team.persistence.jena.objectMappers;

import com.cver.team.model.Type;
import com.cver.team.model.literal.Identifier;
import com.cver.team.persistence.jena.namespaces.CVO;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.vocabulary.RDFS;
import org.springframework.stereotype.Component;

/**
 * Created by PC on 27/08/2016.
 */
@Component
public class TypeObjectMapper {
    public Type generateType(Model model, Resource resource) {
        Type type = new Type();

        // URI
        String uri = resource.getURI();
        Identifier identifier = new Identifier();
        identifier.setURI(uri);
        String name = CVO.getId(uri);
        identifier.setId(name);
        type.setIdentifier(identifier);

        // Name
        type.setName(name);

        // Parents
        StmtIterator parents = resource.listProperties(RDFS.subClassOf);
        while (parents.hasNext()) {
            Resource parent = parents.next().getObject().asResource();
            if (!parent.getURI().equals(resource.getURI()))
                type.addParent(generateType(model, parent));
        }

        return type;
    }
}
