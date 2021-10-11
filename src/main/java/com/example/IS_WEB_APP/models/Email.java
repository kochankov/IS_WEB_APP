package com.example.IS_WEB_APP.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "T_MAILS")
public class Email {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @NotNull
    @Column(name = "EMAIL_TYPE")
    private String emailType;

    @Column(name = "EMAIL")
    private String email;

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "T_PEOPLE_ID")
    private Person person;

    public void setId(int id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public int getId() {
        return id;
    }

    public String getEmailType() {
        return emailType;
    }

    public void setEmailType(String emailType) {
        this.emailType = emailType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
