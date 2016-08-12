package com.cver.team.persistence;


import com.cver.team.model.data.Address;
import com.cver.team.model.entity.Person;
import java.util.List;

public interface AddressRepository {

    Address saveNewAddress(Address address, Person owner);

    List<Address> getAddressesForPerson(Person person);

    Address deleteAddresss(Address address);

}
