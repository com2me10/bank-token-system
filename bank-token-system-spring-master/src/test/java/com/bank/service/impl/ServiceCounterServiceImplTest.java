package com.bank.service.impl;

import com.bank.service.BankTokenSystemApplicationTests;
import com.bank.constants.CustomerType;
import com.bank.constants.ServiceType;
import com.bank.entity.CustomerToken;
import com.bank.entity.ServiceCounter;
import com.bank.repo.CustomerTokenRepo;
import com.bank.repo.ServiceCounterRepo;
import com.bank.service.ServiceCounterService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by bhuvneshwars on 18/5/18.
 */
public class ServiceCounterServiceImplTest extends BankTokenSystemApplicationTests{

    @TestConfiguration
    static class ServiceCounterServiceImplTestContextConfiguration {

        @Bean
        public ServiceCounterService serviceCounterService() {
            return new ServiceCounterServicecImpl();
        }
    }

    @Autowired
    private ServiceCounterService serviceCounterService;
    @MockBean
    private ServiceCounterRepo serviceCounterRepo;
    @MockBean
    private CustomerTokenRepo customerTokenRepo;

    @Before
    public void setUp() {
        ServiceCounter counter1 = new ServiceCounter("D1", ServiceType.DEPOSIT.toString(), CustomerType.PREMIUM.toString(), null);
        ServiceCounter counter2 = new ServiceCounter("D2", ServiceType.DEPOSIT.toString(), CustomerType.REGULAR.toString(), null);
        List<ServiceCounter> counterList = new ArrayList<ServiceCounter>() {{add(counter1); add(counter2);}};
        Mockito.when(serviceCounterRepo.findAll())
                .thenReturn(counterList);

        CustomerToken token1 = new CustomerToken("CREATED", ServiceType.DEPOSIT.toString(), new Date(), null);
        CustomerToken token2 = new CustomerToken("CREATED", ServiceType.DEPOSIT.toString(), new Date(), null);
        List<CustomerToken> tokenList = new ArrayList<CustomerToken>() {{add(token1); add(token2);}};
        Mockito.when(customerTokenRepo.findAll())
                .thenReturn(tokenList);
        Mockito.when(customerTokenRepo.findByServiceType(ServiceType.DEPOSIT.toString()))
                .thenReturn(tokenList);
    }

    @Test
    public void whenFetchAllServiceCounters_thenCounterListShouldBeFound() {
        List<ServiceCounter> counterList = serviceCounterService.getServiceCounterList();

        assert counterList.size() == 2;
        assert counterList.get(0).getName().equalsIgnoreCase("D1");
        assert counterList.get(1).getName().equalsIgnoreCase("D2");
    }

    @Test
    public void whenFetchTokensForServiceCounter_thenTokenListShouldBeFound() {
        List<CustomerToken> tokenList = serviceCounterService.getTokensForServiceCounter("deposit");

        assert tokenList.size() == 2;
        assert tokenList.get(0).getStatus().equalsIgnoreCase("CREATED");
        assert tokenList.get(1).getStatus().equalsIgnoreCase("CREATED");
    }
}
