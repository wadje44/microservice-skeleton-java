package com.ikea.isx.service;

import com.ikea.isx.AbstractTest;
import com.ikea.isx.dao.impl.CustomerDAOImpl;
import com.ikea.isx.model.Customer;
import com.ikea.isx.service.impl.CustomerServiceImpl;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static junit.framework.TestCase.assertTrue;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


public class TestCustomerServiceImpl extends AbstractTest {

    @InjectMocks
    CustomerServiceImpl customerService;

    @Mock
    CustomerDAOImpl mockCustomerDAO;

    @Test
    public void testGetCustomer() {
        when(mockCustomerDAO.getCustomer("one")).thenReturn(Optional.of(new Customer("one","Jacob")));
        Customer customer = customerService.getCustomer("one");
        assertTrue(customer.getCustomerid().equals("one") && customer.getCustomername().equals("Jacob"));
    }

    @Test
    public void testGetCustomerForNull() {
        Customer customer = customerService.getCustomer("notPresent");
        assertThat(customer).isNull();
    }
}
