package com.bank.service.impl;

import com.bank.constants.ServiceType;
import com.bank.entity.CustomerToken;
import com.bank.entity.ServiceCounter;
import com.bank.repo.CustomerTokenRepo;
import com.bank.repo.ServiceCounterRepo;
import com.bank.service.ServiceCounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by bhuvneshwars on 16/5/18.
 */
@Service
public class ServiceCounterServicecImpl implements ServiceCounterService {

    @Autowired
    ServiceCounterRepo serviceCounterRepo;
    @Autowired
    CustomerTokenRepo customerTokenRepo;

    @Override
    public List<ServiceCounter> getServiceCounterList() {
        List<ServiceCounter> serviceCounters = serviceCounterRepo.findAll();
        return serviceCounters;
    }

    @Override
    public List<CustomerToken> getTokensForServiceCounter(String serviceType) {

        String serviceTypes = ServiceType.getServiceType(serviceType);
        if(serviceTypes==null)
            return null;
        List<CustomerToken> customerTokens = customerTokenRepo.findByServiceType(serviceTypes);
        return customerTokens;
    }
}
