package com.bank.entity;


import javax.persistence.*;

@Entity
@Table(name = "branch")
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    String branchName;

    @Column(nullable = false)
    String ifscCode;

    @ManyToOne
//    @JoinColumn(name = "id")
    Bank bank;

    public Branch() {}

    public Branch(String branchName, String ifscCode, Bank bank) {
        this.branchName = branchName;
        this.ifscCode = ifscCode;
        this.bank = bank;
    }

}
