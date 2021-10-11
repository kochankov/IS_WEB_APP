package com.example.IS_WEB_APP.models;

import javax.validation.constraints.NotNull;
import javax.persistence.*;

@Entity
@Table(name = "T_ADDRESSES")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @NotNull
    @Column(name = "ADDR_TYPE")
    private String addressType;

    @Column(name = "ADDR_INFO")
    private String addressInfo;

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "T_PEOPLE_ID")
    private Person person;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

    public String getAddressInfo() {
        return addressInfo;
    }

    public void setAddressInfo(String addressInfo) {
        this.addressInfo = addressInfo;
    }

}
