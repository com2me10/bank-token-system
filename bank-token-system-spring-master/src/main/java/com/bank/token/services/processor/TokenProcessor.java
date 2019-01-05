package com.bank.token.services.processor;

import com.bank.constants.ServiceType;
import com.bank.entity.CustomerToken;
import com.bank.entity.ServiceCounter;
import com.bank.token.services.counter.IBankServiceCounter;
import com.bank.token.services.factory.CounterServiceFactory;

/**
 * Created by bhuvneshwars on 16/5/18.
 */
public class TokenProcessor {

    /**
     * This method will add customer token to task queue
     * @param token
     */
    public static void addTokenToQueue(CustomerToken token) {

        IBankServiceCounter serviceCounter = CounterServiceFactory.getServiceCounterInstance(ServiceType.getService(token.getServiceType()));
        serviceCounter.addTokenToQueue(token);
    }

    /**
     * This method will process token and will remove from task queue
     * @param counter
     * @return token
     */
    public static CustomerToken processToken(ServiceCounter counter) {

        IBankServiceCounter serviceCounter = CounterServiceFactory.getServiceCounterInstance(ServiceType.getService(counter.getServiceType()));
        CustomerToken token = serviceCounter.processToken(counter.getCounterType());
        return token;
    }
}
