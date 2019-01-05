package com.bank.controller;

import com.bank.service.impl.TestDataServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestDataController {

    @Autowired
    TestDataServiceImpl testDataImpl;

    @RequestMapping(value = "/dummyData", method = RequestMethod.GET)
    public String createBankDummyData() {

        try{
            boolean created = testDataImpl.createBankDummyData();
            if(created) {
                return "Dummy data is created successfully";
            } else {
                return "Dummy data is already available in db";
            }
        } catch(Exception e) {
            return "Unable to create dummy data";
        }
    }

}
