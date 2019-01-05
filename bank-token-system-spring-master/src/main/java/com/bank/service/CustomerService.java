package com.bank.service;

import com.bank.entity.Customer;

import java.util.List;

public interface CustomerService {

    Customer getCustomer(String phoneNumber);

    List<Customer> getCustomerList();

    void createCustomer(Customer customer);
}
