package com.example.IS_WEB_APP.services.interfaces;

import com.example.IS_WEB_APP.models.Email;

import java.util.List;

public interface EmailService {
    List<Email> getAll();
    Email getEmailById(int id);
    void create(Email email);
    void update(int emailId, Email email);
    void delete(int emailId);
}
