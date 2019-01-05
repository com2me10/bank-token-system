package com.bank.service.impl;

import com.bank.constants.ServiceType;
import com.bank.entity.Customer;
import com.bank.entity.CustomerToken;
import com.bank.entity.ServiceCounter;
import com.bank.repo.CustomerRepo;
import com.bank.repo.CustomerTokenRepo;
import com.bank.repo.ServiceCounterRepo;
import com.bank.service.TokenService;
import com.bank.token.services.processor.TokenProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    CustomerRepo customerRepo;
    @Autowired
    ServiceCounterRepo serviceCounterRepo;
    @Autowired
    CustomerTokenRepo customerTokenRepo;

    @Override
        public String generateToken(String phoneNumber, String serviceType) {

        Customer customer = customerRepo.findByPhoneNumber(phoneNumber);
        if(customer != null) {

            CustomerToken token = new CustomerToken("CREATED", ServiceType.getServiceType(serviceType), new Date(), customer);
            customerTokenRepo.save(token);

            TokenProcessor.addTokenToQueue(token);

            return "Token ("+token.getTokenNumber()+") is generated successfully.";

        } else {

            return "Any custommer is not registered with this number, Please create new customer";
        }
    }

    @Override
    public String processToken(String counterName) {

        ServiceCounter counter = serviceCounterRepo.findByName(counterName);
        if(counter == null) {
            return "Counter "+counterName+"does not exist, Please provide valid name";
        }
        CustomerToken token = TokenProcessor.processToken(counter);

        if(token == null) {
            return "No token found for "+counter.getServiceType()+" counter "+counterName;
        }

        String message = "Token ("+token.getTokenNumber()+") is processed at "+counterName+" for customer "+token.getCustomer().getName();

        if(token.getStatus().equalsIgnoreCase("IN PROGRESS")) {

            message = message+" and next counter is "+token.getServiceType();
        }

        customerTokenRepo.save(token);

        return message;
    }
}
