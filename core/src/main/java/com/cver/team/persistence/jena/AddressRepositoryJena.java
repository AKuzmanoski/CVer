package com.cver.team.persistence.jena;

import com.cver.team.model.data.Address;
import com.cver.team.model.entity.Person;
import com.cver.team.model.literal.Identifier;
import com.cver.team.persistence.AddressRepository;
import com.cver.team.persistence.jena.helper.JenaPreferences;
import com.cver.team.persistence.jena.helper.ResourcePrefixes;
import com.cver.team.persistence.jena.helper.SPARQLPrefix;
import org.apache.jena.query.*;
import org.apache.jena.update.UpdateExecutionFactory;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateProcessor;
import org.apache.jena.update.UpdateRequest;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class AddressRepositoryJena implements AddressRepository {
    @Override
    public Address saveNewAddress(Address address, Person owner) {

        String languageTag = "en";

        String city = address.getCity();
        String country = address.getCountry();
        String street = address.getStreet();
        String addressID = UUID.randomUUID().toString();
        String ownerURI = owner.getIdentifier().getURI();

        ParameterizedSparqlString queryString = new ParameterizedSparqlString();

        queryString.setNsPrefix("cver", SPARQLPrefix.cvr);
        queryString.setNsPrefix("cvo", SPARQLPrefix.cvo);

        queryString.setCommandText("INSERT { \n" +
                " ?ownerUri cvo:address ?address . \n" +
                " ?address cvo:city ?city ; \n" +
                "          cvo:country ?country  ;  \n " +
                "          cvo:street ?street . \n " +
                " } WHERE { }  "
        );

        queryString.setIri("ownerUri",ownerURI);
        queryString.setIri("address", ResourcePrefixes.ADDRESS_PREFIX + addressID);

        if(city != null)
        queryString.setLiteral("city", city, languageTag);

        if(country != null)
        queryString.setLiteral("country",country, languageTag);

        if(street != null)
        queryString.setLiteral("street",street, languageTag);

        System.out.println(queryString.toString());

        UpdateRequest updateRequest = UpdateFactory.create(queryString.toString());
        UpdateProcessor updateProcessor = UpdateExecutionFactory.createRemote(updateRequest, JenaPreferences.UpdateEndpoint);
        updateProcessor.execute();

        System.out.println("AN ADDRESS WAS SUCCESSFULLY CREATED !");

        Identifier identifier = new Identifier();
        identifier.setId(addressID);
        identifier.setURI(ResourcePrefixes.ADDRESS_PREFIX + addressID);
        address.setIdentifier(identifier);
        return address;

    }

    @Override
    public List<Address> getAddressesForPerson(Person person) {

        String personURI = person.getIdentifier().getURI();

        String languageTag = "en";

        ParameterizedSparqlString queryString = new ParameterizedSparqlString();
        queryString.setNsPrefix("cvo", SPARQLPrefix.cvo);
        queryString.setNsPrefix("cver", SPARQLPrefix.cvr);


        queryString.setCommandText(
                " SELECT ?addressURI ?city ?country ?street \n " +
                " WHERE { \n" +
                " ?userURI cvo:address ?addressURI . \n" +

                        " OPTIONAL { ?addressURI cvo:city ?city . \n" +
                            " FILTER (lang(?city) = 'en') }  \n" +

                        " OPTIONAL { ?addressURI cvo:country ?country . \n "+
                        " FILTER (lang(?country) = 'en') } \n "+

                        " OPTIONAL { ?addressURI cvo:street ?street . \n"+
                        " FILTER (lang(?street) = 'en') } \n "+


        "}");

        queryString.setIri("userURI",personURI);

        System.out.println(queryString.toString());

        Query query = QueryFactory.create(queryString.toString());
        QueryExecution queryExecution = QueryExecutionFactory.sparqlService(JenaPreferences.SPARQLEndpoint, query);
        ResultSet set = queryExecution.execSelect();

        List<Address> addresses = new ArrayList<Address>();

        while(set.hasNext()) {

            QuerySolution currentEntry = set.next();
            Address address = new Address();

            Identifier identifier = new Identifier();
            identifier.setURI(currentEntry.get("addressURI").toString());
            address.setIdentifier(identifier);

            if(currentEntry.get("city") != null)
            address.setCity(currentEntry.get("city").toString());

            if(currentEntry.get("country") != null)
            address.setCountry(currentEntry.get("country").toString());

            if(currentEntry.get("street") != null)
            address.setStreet(currentEntry.get("street").toString());

            addresses.add(address);
        }

        return addresses;
    }

    @Override
    public Address deleteAddresss(Address address) {

        String languageTag = "en";



        ParameterizedSparqlString queryString = new ParameterizedSparqlString();

        queryString.setNsPrefix("cvo", SPARQLPrefix.cvo);
        queryString.setNsPrefix("cver", SPARQLPrefix.cvr);

        queryString.setCommandText("DELETE WHERE { \n" +
                " ?addressURI ?p ?o . \n " +
                " ?userURI cvo:address ?addressURI . " +
                    "}");

        System.out.println(queryString.toString());

        queryString.setIri("addressURI",address.getIdentifier().getURI());


        UpdateRequest updateRequest = UpdateFactory.create(queryString.toString());
        UpdateProcessor updateProcessor = UpdateExecutionFactory.createRemote(updateRequest, JenaPreferences.UpdateEndpoint);
        updateProcessor.execute();

        return address;

    }
}
