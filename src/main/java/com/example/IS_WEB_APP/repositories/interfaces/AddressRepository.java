package com.example.IS_WEB_APP.repositories.interfaces;

import com.example.IS_WEB_APP.models.Address;

import java.util.List;

public interface AddressRepository {
    Address getAddressById(int id);
    List<Address> getAll();
    void create(Address assignment);
    void update(Address assignment);
    void delete(Address assignment);

}
