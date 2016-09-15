package com.cver.team.persistence.jena.objectMappers.dataObjectMappers;

import com.cver.team.model.data.Email;
import com.cver.team.persistence.jena.helper.IdentifierGenerator;
import com.cver.team.persistence.jena.namespaces.CVO;
import com.cver.team.persistence.jena.namespaces.CVR;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.impl.LiteralImpl;
import org.apache.jena.rdf.model.impl.StatementImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Created
 * by PC on 25/08/2016.
 */
@Component
public class EmailObjectMapper {
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    DataObjectMapper dataObjectMapper;

    public Email generateEmail(Model model, Resource resource) {
        Email email = new Email();
        email = dataObjectMapper.generateData(model, resource, email);

        // mbox
        Statement statement = resource.getProperty(CVO.getProperty("mbox"));
        if (statement != null) {
            email.setMbox(statement.getObject().asLiteral().getString());
        }

        // hashedMbox
        statement = resource.getProperty(CVO.getProperty("hashedMbox"));
        if (statement != null) {
            email.setHashedMbox(statement.getObject().asLiteral().getString());
        }

        return email;
    }

    public void createModel(Email email, Model model) {
        if (email.getIdentifier() != null)
            return;
        email.setIdentifier(IdentifierGenerator.generateIdentifier());
        Resource resource = CVR.getResource(email.getIdentifier().getId());
        createModel(email, model, resource);
    }

    private void createModel(Email email, Model model, Resource resource) {
        dataObjectMapper.createModel(email, model, resource);

        if (email.getMbox() != null) {
            Literal mbox = model.createLiteral(email.getMbox());
            model.add(new StatementImpl(resource, CVO.getProperty("mbox"), mbox));
            Literal hashedMbox = model.createLiteral(bCryptPasswordEncoder.encode(email.getMbox()));
            model.add(new StatementImpl(resource, CVO.getProperty("hasedMbox"), hashedMbox));
        }
    }
}
