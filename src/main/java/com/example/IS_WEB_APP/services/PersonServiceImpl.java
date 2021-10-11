package com.example.IS_WEB_APP.services;

import com.example.IS_WEB_APP.models.Person;
import com.example.IS_WEB_APP.repositories.interfaces.PersonRepository;
import com.example.IS_WEB_APP.services.interfaces.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public List<Person> getAll() {
        return personRepository.getAll();
    }

    @Override
    public Person getPersonById(int id) {
        return personRepository.getPersonById(id);
    }

    public List<Person> getPersonByName(String name){
        return personRepository.getPersonByName(name);
    }


    @Override
    public void create(Person person) throws Exception {

        person.getEmail().setPerson(person);
        person.getAddress().setPerson(person);

        try {
            int res = personRepository.create(person);
        } catch(Exception ex){
            throw new Exception("error creating person " + ex.getMessage());
        }
    }

    @Override
    public void update(int personId, Person person) throws Exception {

        try {
            Person persistedPerson = personRepository.getPersonById(personId);
            persistedPerson.getAddress().setAddressInfo(person.getAddress().getAddressInfo());
            persistedPerson.getAddress().setAddressType(person.getAddress().getAddressType());
            persistedPerson.getEmail().setEmailType(person.getEmail().getEmailType());
            persistedPerson.getEmail().setEmail(person.getEmail().getEmail());
            persistedPerson.setPin(person.getPin());
            persistedPerson.setFullName(person.getFullName());
            personRepository.update(persistedPerson);
        } catch(Exception ex){
            throw new Exception("error editing person " + ex.getMessage());
        }
    }

    @Override
    public void delete(int personId) {
        Person person = personRepository.getPersonById(personId);
        personRepository.delete(person);
    }

}