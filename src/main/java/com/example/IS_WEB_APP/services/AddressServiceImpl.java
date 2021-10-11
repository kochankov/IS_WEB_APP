package com.example.IS_WEB_APP.services;

import com.example.IS_WEB_APP.models.Address;
import com.example.IS_WEB_APP.repositories.interfaces.AddressRepository;
import com.example.IS_WEB_APP.services.interfaces.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public List<Address> getAll() {
        return addressRepository.getAll();
    }

    @Override
    public Address getAddressById(int id) {
        return addressRepository.getAddressById(id);
    }

    @Override
    public void create(Address address) {
        addressRepository.create(address);
    }

    @Override
    public void update(int addressId, Address address) {
        address.setId(addressId);
        addressRepository.update(address);
    }

    @Override
    public void delete(int addressId) {
        Address address = addressRepository.getAddressById(addressId);
        addressRepository.delete(address);
    }

}
