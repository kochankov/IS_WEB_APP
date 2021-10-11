package com.example.IS_WEB_APP.repositories.interfaces;

import com.example.IS_WEB_APP.models.Email;

import java.util.List;

public interface EmailRepository {
    Email getEmailById(int id);
    List<Email> getAll();
    void create(Email email);
    void update(Email email);
    void delete(Email email);
}
