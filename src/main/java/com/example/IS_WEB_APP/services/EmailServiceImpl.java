package com.example.IS_WEB_APP.services;

import com.example.IS_WEB_APP.models.Email;
import com.example.IS_WEB_APP.repositories.interfaces.EmailRepository;
import com.example.IS_WEB_APP.services.interfaces.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailServiceImpl implements EmailService {
    private final EmailRepository emailRepository;

    @Autowired
    public EmailServiceImpl(EmailRepository emailRepository) {
        this.emailRepository = emailRepository;
    }

    @Override
    public List<Email> getAll() {
        return emailRepository.getAll();
    }

    @Override
    public Email getEmailById(int id) {
        return emailRepository.getEmailById(id);
    }

    @Override
    public void create(Email email) {
        emailRepository.create(email);
    }

    @Override
    public void update(int emailId, Email email) {
        email.setId(emailId);
        emailRepository.update(email);
    }

    @Override
    public void delete(int emailId) {
        Email email = emailRepository.getEmailById(emailId);
        emailRepository.delete(email);
    }

}