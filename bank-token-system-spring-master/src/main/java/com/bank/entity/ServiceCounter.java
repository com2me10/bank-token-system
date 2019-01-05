package com.bank.entity;

import javax.persistence.*;

@Entity
@Table(name = "serviceCounter")
public class ServiceCounter {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false, name = "name")
    String name;

    @Column(nullable = false)
    String serviceType;  // Which service provides like deposit, withdraw, enquiery etc.

    @Column(nullable = false)
    String counterType;  // Which customer type serve like premium, regular

    @ManyToOne
    Branch branch;

    public ServiceCounter() {}

    public ServiceCounter(String name, String serviceType, String counterType, Branch branch) {
        this.name = name;
        this.serviceType = serviceType;
        this.counterType = counterType;
        this.branch = branch;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getServiceType() {
        return serviceType;
    }

    public String getCounterType() {
        return counterType;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public void setCounterType(String counterType) {
        this.counterType = counterType;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }
}
