package com.bank.service.impl;

import com.bank.constants.CustomerType;
import com.bank.service.BankTokenSystemApplicationTests;
import com.bank.constants.ServiceType;
import com.bank.entity.Customer;
import com.bank.repo.CustomerRepo;
import com.bank.service.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bhuvneshwars on 17/5/18.
 */
public class CustomerServiceImplTest extends BankTokenSystemApplicationTests{

    @TestConfiguration
    static class CustomerServiceImplTestContextConfiguration {

        @Bean
        public CustomerService customerService() {
            return new CustomerServiceImpl();
        }
    }

    @Autowired
    private CustomerService customerService;
    @MockBean
    private CustomerRepo customerRepo;
    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        Customer customer1 = new Customer("Bhuvi", "7897952579", "Hyderabad", CustomerType.PREMIUM.toString());
        Mockito.when(customerRepo.findByPhoneNumber(customer1.getPhoneNumber()))
                .thenReturn(customer1);

        Customer customer2 = new Customer("Alex", "6782342434", "Hyderabad", CustomerType.REGULAR.toString());
        List<Customer> customerList = new ArrayList<Customer>(){{add(customer1); add(customer2);}};

        Mockito.when(customerRepo.findAll())
                .thenReturn(customerList);
    }

    @Test
    public void whenValidPhoneNumber_thenCustomerShouldBeFound() {
        String phoneNumber = "7897952579";
        Customer found = customerService.getCustomer(phoneNumber);

        assert found.getPhoneNumber().equalsIgnoreCase(phoneNumber);
    }

    @Test
    public void whenFetchAllCustomers_thenCustomerListShouldBeFound() {
        List<Customer> customerList = customerService.getCustomerList();

        assert customerList.size() == 2;
        assert customerList.get(0).getName().equalsIgnoreCase("Bhuvi");
        assert customerList.get(1).getName().equalsIgnoreCase("Alex");
    }

    @Test
    public void canCreateANewCustomer() throws Exception {

        Customer customer = new Customer("John", "6782342434", "Hyderabad", ServiceType.DEPOSIT.toString());
        customerRepo.save(customer);
        String exampleCourseJson = "{\"name\":\"John\",\"phoneNumber\":\"6782342434\",\"address\":\"Hyderabad\",\"serviceType\":\"premium\"}";

        mockMvc.perform(post("/customer").content(exampleCourseJson).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }
}
