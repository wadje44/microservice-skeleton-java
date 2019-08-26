package com.ikea.isx.dao;

import com.ikea.isx.AbstractTest;
import com.ikea.isx.dao.impl.CustomerDAOImpl;
import com.ikea.isx.dao.interfaces.CustomerRepository;
import com.ikea.isx.model.Customer;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static junit.framework.TestCase.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class TestCustomerDAOImpl  extends AbstractTest {

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @InjectMocks
    CustomerDAOImpl customerDAO;

    @Mock
    CustomerRepository mockCustomerRepository;

    @Test
    public void testGetCustomer() {
        when(mockCustomerRepository.findById(anyString())).thenReturn(Optional.of(new Customer("one","Jacob")));
        assertTrue(customerDAO.getCustomer("one").isPresent());
    }

}