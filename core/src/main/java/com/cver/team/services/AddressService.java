package com.cver.team.services;


import com.cver.team.model.data.Address;
import com.cver.team.model.entity.Person;

import java.util.List;

public interface AddressService {

    Address saveNewAddress(Address address, Person owner);

    List<Address> getAddressesForPerson(Person person);

    Address deleteAddress(Address address);
}
