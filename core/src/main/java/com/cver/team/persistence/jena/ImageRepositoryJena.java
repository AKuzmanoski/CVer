package com.cver.team.persistence.jena;

import com.cver.team.config.StaticConstants;
import com.cver.team.model.data.Image;
import com.cver.team.model.entity.Person;
import com.cver.team.model.literal.Identifier;
import com.cver.team.persistence.ImageRepository;
import com.cver.team.persistence.jena.helper.ByteConverter;
import com.cver.team.persistence.jena.helper.JenaPreferences;
import com.cver.team.persistence.jena.helper.ResourcePrefixes;
import com.cver.team.persistence.jena.helper.SPARQLPrefix;
import org.apache.jena.query.ParameterizedSparqlString;
import org.apache.jena.update.UpdateExecutionFactory;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateProcessor;
import org.apache.jena.update.UpdateRequest;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class ImageRepositoryJena implements ImageRepository {

    @Override
    public Image createNewImage(Image image, Person owner, String pictureUse) {

        String language = "en";

        String imageData = ByteConverter.convertBytesToString(image.getImageData());
        String contentType = image.getContentyType();
        String imageID = UUID.randomUUID().toString();
        String ownerURI = owner.getIdentifier().getURI();
       // String description = image.getDescription();

        ParameterizedSparqlString queryString = new ParameterizedSparqlString();

        queryString.setNsPrefix("cver", SPARQLPrefix.cvr);
        queryString.setNsPrefix("cvo", SPARQLPrefix.cvo);

        queryString.setCommandText("INSERT { \n" +
                " ?ownerUri "+pictureUse+" ?image . \n" +
                " ?image cvo:imageContentType ?contentType ; \n" +
                "        cvo:imageURL ?fullImageURL ;  \n " +
                "        cvo:imageData ?data ; \n " +
                "        cvo:description ?description .  \n " +
                " } WHERE { }  "
        );

        queryString.setIri("ownerUri", ownerURI);

        queryString.setLiteral("contentType",contentType);

        queryString.setIri("image", ResourcePrefixes.IMAGE_PREFIX+imageID);

        queryString.setLiteral("fullImageURL", StaticConstants.IMAGE_ENDPOINT_PREFIX+imageID);

        queryString.setLiteral("data", imageData);

    //    if(description != null)
    //    queryString.setLiteral("description",description,language);

        System.out.println(queryString.toString());
        UpdateRequest updateRequest = UpdateFactory.create(queryString.toString());
        UpdateProcessor updateProcessor = UpdateExecutionFactory.createRemote(updateRequest, JenaPreferences.UpdateEndpoint);
        updateProcessor.execute();
        System.out.println("UPDATE WAS SUCCESFULL");

        Identifier identifier = new Identifier();
        identifier.setURI(ResourcePrefixes.IMAGE_PREFIX + imageID);
        identifier.setId(imageID);
        image.setImageURL(StaticConstants.IMAGE_ENDPOINT_PREFIX + imageID);
        image.setIdentifier(identifier);
        return image;

    }

    @Override
    public Image deleteImage(Image image) {

        String languageTag = "en";



        ParameterizedSparqlString queryString = new ParameterizedSparqlString();

        queryString.setNsPrefix("cvo", SPARQLPrefix.cvo);
        queryString.setNsPrefix("cver", SPARQLPrefix.cvr);

        queryString.setCommandText("DELETE WHERE { \n" +
                " ?imageURI ?p ?o . \n " +
                " ?userURI cvo:address ?addressURI . " +
                "}");

        System.out.println(queryString.toString());
        return null;

   //     queryString.setIri("addressURI",address.getIdentifier().getURI());

    }

    @Override
    public Image getImageByURL(String URL) {
        return null;
    }
}
