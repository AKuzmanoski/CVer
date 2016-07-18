package com.cver.team.services.impl;


import com.cver.team.model.Address;
import com.cver.team.model.Person;
import com.cver.team.persistence.AddressRepository;
import com.cver.team.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    AddressRepository addressRepository;

    @Override
    public Address saveNewAddress(Address address, Person owner) {
        return addressRepository.saveNewAddress(address,owner);
    }

    @Override
    public List<Address> getAddressesForPerson(Person person) {
        return addressRepository.getAddressesForPerson(person);
    }

    @Override
    public Address deleteAddress(Address address) {
        return addressRepository.deleteAddresss(address);
    }
}
