package com.bank.controller;

import com.bank.entity.Customer;
import com.bank.service.impl.CustomerServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    CustomerServiceImpl customerService;

    @RequestMapping(value = "/customer/{phoneNumber}", method = RequestMethod.GET, produces = "application/json")
    Customer getCustomer(@PathVariable String phoneNumber) {

        Customer customer = null;
        try {
            customer = customerService.getCustomer(phoneNumber);
        } catch (Exception e) {
            logger.error("Facing issue while fetching customer details : " + e.getMessage());
        }

        return customer;
    }

    @RequestMapping(value = "/customer", method = RequestMethod.GET)
    List<Customer> getCustomerList() {

        List<Customer> customerList = null;
        try {
            customerList = customerService.getCustomerList();
        } catch (Exception e) {
            logger.error("Facing issue while fetching customer list : " + e.getMessage());
        }
        return customerList;
    }

    @RequestMapping(value = "/customer", method = RequestMethod.POST)
    String CreateCustomer(@RequestBody Customer customer) {

        try {
            customerService.createCustomer(customer);
            return "Customer is created successfully";

        } catch (Exception e) {
            logger.error("Facing issue while creating customer record");
            return "Facing issue while creating customer";
        }
    }
}
