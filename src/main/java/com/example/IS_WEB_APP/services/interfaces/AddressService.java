package com.example.IS_WEB_APP.services.interfaces;

import com.example.IS_WEB_APP.models.Address;

import java.util.List;

public interface AddressService {
    List<Address> getAll();
    Address getAddressById(int id);
    void create(Address address);
    void update(int addressId, Address address);
    void delete(int addressId);
}
