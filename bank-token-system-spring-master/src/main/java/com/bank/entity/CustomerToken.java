package com.bank.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "customerToken")
public class CustomerToken {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long tokenNumber;

    @Column(nullable = false)
    String status;

    @Column(nullable = false, name = "serviceType")
    String serviceType;

    @Column(nullable = false)
    Date currDate;

    @OneToOne
    Customer customer;

    public CustomerToken() {}

    public CustomerToken(String status, String serviceType, Date currDate, Customer customer) {

        this.status = status;
        this.serviceType = serviceType;
        this.currDate = currDate;
        this.customer = customer;
    }

    public long getTokenNumber() {
        return tokenNumber;
    }

    public Date getCurrDate() {
        return currDate;
    }

    public String getStatus() {
        return status;
    }

    public String getServiceType() {
        return serviceType;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setTokenNumber(Integer tokenNumber) {
        this.tokenNumber = tokenNumber;
    }

    public void setCurrDate(Date currDate) {
        this.currDate = currDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
