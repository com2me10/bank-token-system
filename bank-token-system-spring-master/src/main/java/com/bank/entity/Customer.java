package com.bank.entity;

import javax.persistence.*;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    String name;

    @Column(nullable = false, name = "phoneNumber")
    String  phoneNumber;

    @Column(nullable = false)
    String  address;

    @Column(nullable = false)
    String  serviceType; // it can be premium or regular as defined in CustomerType

    public Customer() {}

    public Customer(String name, String phoneNumber, String address, String serviceType) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.serviceType = serviceType;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }
}
