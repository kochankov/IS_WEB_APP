package com.example.IS_WEB_APP.services.interfaces;

import com.example.IS_WEB_APP.models.Address;
import com.example.IS_WEB_APP.models.Email;
import com.example.IS_WEB_APP.models.Person;

import java.util.List;

public interface PersonService {
    List<Person> getAll();
    Person getPersonById(int id);
    void create(Person person) throws Exception;
    //void create(Person person);
    void update(int personId, Person person) throws Exception;
    void delete(int personId);

    List<Person> getPersonByName(String name);
}
