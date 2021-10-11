package com.example.IS_WEB_APP.repositories;

import com.example.IS_WEB_APP.models.Address;
import com.example.IS_WEB_APP.repositories.interfaces.AddressRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class AddressRepositoryImpl implements AddressRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    public AddressRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Address getAddressById(int id) {
        Session session = setupSession();
        return session.get(Address.class, id);
    }

    @Override
    public List<Address> getAll() {
        Session session = setupSession();
        Query<Address> query = session.createQuery("from Address", Address.class);
        return query.list();
    }

    @Override
    public void create(Address address) {
        Session session = setupSession();
        session.save(address);
    }

    @Override
    public void update(Address address) {
        Session session = setupSession();
        session.update(address);
    }

    @Override
    public void delete(Address address) {
        Session session = setupSession();
        session.delete(address);
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