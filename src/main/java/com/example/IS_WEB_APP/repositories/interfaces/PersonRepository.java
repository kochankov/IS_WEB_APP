package com.example.IS_WEB_APP.repositories.interfaces;

import com.example.IS_WEB_APP.models.Person;

import java.util.List;

public interface PersonRepository {
    Person getPersonById(int id);
    List<Person> getAll();
    int create(Person person);
    void update(Person person);
    void delete(Person person);
    List<Person> getPersonByName(String name);
}
