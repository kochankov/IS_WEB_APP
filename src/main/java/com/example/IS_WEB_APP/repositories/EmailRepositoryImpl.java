package com.example.IS_WEB_APP.repositories;

import com.example.IS_WEB_APP.models.Email;
import com.example.IS_WEB_APP.repositories.interfaces.EmailRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class EmailRepositoryImpl implements EmailRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    public EmailRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Email getEmailById(int id) {
        Session session = setupSession();
        return session.get(Email.class, id);
    }

    @Override
    public List<Email> getAll() {
        Session session = setupSession();
        Query<Email> query = session.createQuery("from Email", Email.class);
        return query.list();
    }

    @Override
    public void create(Email email) {
        Session session = setupSession();
        session.save(email);
    }

    @Override
    public void update(Email email) {
        Session session = setupSession();
        session.update(email);
    }

    @Override
    public void delete(Email email) {
        Session session = setupSession();
        session.delete(email);
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
