package com.bank.controller;

import com.bank.entity.CustomerToken;
import com.bank.entity.ServiceCounter;
import com.bank.service.impl.ServiceCounterServicecImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ServiceCounterController {

    private static final Logger logger = LoggerFactory.getLogger(ServiceCounterController.class);

    @Autowired
    ServiceCounterServicecImpl serviceCounter;

    @RequestMapping(value = "/serviceCounter", method = RequestMethod.GET)
    List<ServiceCounter> getServiceCounterList() {

        List<ServiceCounter> serviceCounterList = null;
        try {
            serviceCounterList =  serviceCounter.getServiceCounterList();

        } catch(Exception e) {
            logger.error("Facing issue while fetching service counter's list : "+e.getMessage());
        }

        return serviceCounterList;
    }

    @RequestMapping(value = "/serviceCounter/{serviceType}/tokens", method = RequestMethod.GET)
    List<CustomerToken> getTokensForServiceCounter(@PathVariable String serviceType) {

        List<CustomerToken> tokens = null;
        try {
            tokens = serviceCounter.getTokensForServiceCounter(serviceType);

        } catch(Exception e) {
            logger.error("Facing issue while fetching token's list for service counter : "+e.getMessage());
        }
        return tokens;
    }
}
