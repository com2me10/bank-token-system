package com.bank.service.impl;

import com.bank.constants.CustomerType;
import com.bank.constants.ServiceType;
import com.bank.entity.Customer;
import com.bank.entity.CustomerToken;
import com.bank.entity.ServiceCounter;
import com.bank.repo.CustomerRepo;
import com.bank.repo.CustomerTokenRepo;
import com.bank.repo.ServiceCounterRepo;
import com.bank.service.BankTokenSystemApplicationTests;
import com.bank.service.TokenService;
import com.bank.token.services.counter.IBankServiceCounter;
import com.bank.token.services.factory.CounterServiceFactory;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;


/**
 * Created by bhuvneshwars on 17/5/18.
 */
public class TokenServiceImplTest extends BankTokenSystemApplicationTests {

    @TestConfiguration
    static class TokenServiceImplTestContextConfiguration {

        @Bean
        public TokenService tokenService() {
            return new TokenServiceImpl();
        }
    }

    @Autowired
    private TokenService tokenService;
    @MockBean
    CustomerRepo customerRepo;
    @MockBean
    ServiceCounterRepo serviceCounterRepo;
    @MockBean
    CustomerTokenRepo customerTokenRepo;

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @Before
    public void setUp() {

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        Customer customer = new Customer("Bhuvi", "7897952579", "Hyderabad", CustomerType.PREMIUM.toString());
        CustomerToken customerToken = new CustomerToken("CREATED", ServiceType.DEPOSIT.toString(), new Date(), customer);
        ServiceCounter counter = new ServiceCounter("D1", ServiceType.DEPOSIT.toString(), CustomerType.PREMIUM.toString(), null);

        Mockito.when(customerRepo.findByPhoneNumber(customer.getPhoneNumber())).thenReturn(customer);

        Mockito.when(customerTokenRepo.save(customerToken)).thenReturn(customerToken);

        Mockito.when(serviceCounterRepo.findByName("D1")).thenReturn(counter);
    }

    @Test
    public void whenGenerateToken_thenTokenShouldBeCreated() throws Exception {

        String message = tokenService.generateToken("7897952579", "deposit");

        IBankServiceCounter serviceCounter = CounterServiceFactory.getServiceCounterInstance(ServiceType.getService("deposit"));

        assert serviceCounter.pendingTokens() == 1;
        assert message.contains("generated successfully.");
    }

    @Test
    public void whenGenerateTokenWithUnknownNumber_thenTokenShouldNotBeCreated() throws Exception {

        IBankServiceCounter serviceCounter = CounterServiceFactory.getServiceCounterInstance(ServiceType.getService("deposit"));
        int noOfTokens = serviceCounter.pendingTokens();

        String message = tokenService.generateToken("7897952000", "deposit");
        assert serviceCounter.pendingTokens() == noOfTokens;
        assert message.contains("Any custommer is not registered with this number, Please create new customer");
    }

    @Test
    public void whenProcessToken_thenTokenShouldBeProcessed() throws Exception {

        IBankServiceCounter serviceCounter = CounterServiceFactory.getServiceCounterInstance(ServiceType.getService("deposit"));
        int noOfTokens = serviceCounter.pendingTokens();

        String message = tokenService.generateToken("7897952579", "deposit");

        assert serviceCounter.pendingTokens() == noOfTokens+1;
        assert message.contains("generated successfully.");

        message = tokenService.processToken("D1");
        assert serviceCounter.pendingTokens() == noOfTokens;
        assert message.contains("is processed");
    }

    @Test
    public void whenProcessTokenWithWrongCounterName_thenTokenShouldNotBeProcessed() throws Exception {

        IBankServiceCounter serviceCounter = CounterServiceFactory.getServiceCounterInstance(ServiceType.getService("deposit"));
        int noOfTokens = serviceCounter.pendingTokens();

        String message = tokenService.generateToken("7897952579", "deposit");

        assert serviceCounter.pendingTokens() == noOfTokens+1;
        assert message.contains("generated successfully.");

        message = tokenService.processToken("D34");
        assert message.contains("does not exist, Please provide valid name");

        message = tokenService.processToken("D1");
        assert serviceCounter.pendingTokens() == noOfTokens;
        assert message.contains("is processed");
    }

    @Test
    public void whenProcessTokenIfQueueEmpty() throws Exception {

        IBankServiceCounter serviceCounter = CounterServiceFactory.getServiceCounterInstance(ServiceType.getService("deposit"));

        assert serviceCounter.pendingTokens() == 0;

        String message = tokenService.processToken("D1");
        assert message.contains("No token found");
    }
}
