package com.bank.token.services.factory;

import com.bank.constants.ServiceType;
import com.bank.token.services.counter.*;

/**
 * Created by bhuvneshwars on 16/5/18.
 */
public class CounterServiceFactory {

    public static IBankServiceCounter getServiceCounterInstance(ServiceType serviceType) {

        IBankServiceCounter serviceCounterObj = null;

        switch (serviceType) {

            case ACCOUNT :
                serviceCounterObj = AccountServiceCounter.getInstance();
                break;

            case DEPOSIT :
                serviceCounterObj = DepositServiceCounter.getInstance();
                break;

            case WITHDRAW:
                serviceCounterObj = WithdrawServiceCounter.getInstance();
                break;

            case ENQUIERY:
                serviceCounterObj = EnquieryServiceCounter.getInstance();
                break;
        }

        return serviceCounterObj;
    }
}
