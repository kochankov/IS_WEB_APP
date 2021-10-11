package com.example.IS_WEB_APP.repositories;

import com.example.IS_WEB_APP.models.Person;
import com.example.IS_WEB_APP.repositories.interfaces.PersonRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class PersonRepositoryImpl implements PersonRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    public PersonRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Person getPersonById(int id) {
        Session session = setupSession();
        return session.get(Person.class, id);
    }

    @Override
    public List<Person> getPersonByName(String name){
        Session session = setupSession();
        Query<Person> query = session.createQuery("from Person where fullName = :name", Person.class);
        query.setParameter("name", name);
        return query.list();
    }

    @Override
    public List<Person> getAll() {
        Session session = setupSession();
        Query<Person> query = session.createQuery("from Person", Person.class);
        return query.list();
    }


    @Override
    public int create(Person person) {
        Session session = setupSession();
        return (int)session.save(person);
    }


    @Override
    public void update(Person person) {
        Session session = setupSession();
        session.clear();
        session.saveOrUpdate(person);
    }

    @Override
    public void delete(Person person) {
        Session session = setupSession();
        session.delete(person);
    }

    private Session setupSession() {
        Session session;
        try {
            session = sessionFactory.getCurrentSession();
        } catch (Exception e) {
            session = sessionFactory.openSession();
        }
        return session;
    }
}
