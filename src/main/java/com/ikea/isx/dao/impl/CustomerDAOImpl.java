package com.ikea.isx.dao.impl;

import com.ikea.isx.dao.interfaces.CustomerRepository;
import com.ikea.isx.dao.interfaces.CustomerDAO;
import com.ikea.isx.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

@Component
@Slf4j
public class CustomerDAOImpl implements CustomerDAO {

    @Autowired
    CustomerRepository customerRepository;

    List<Customer> customerList;

    // To load the data in customer table before creating any object of this class. It gets called while testion too.
    @PostConstruct
    private void postConstruct() {
        Customer customer1 = new Customer("one", "Jacob");
        Customer customer2 = new Customer("two", "Allen");
        customerRepository.save(customer1);
        customerRepository.save(customer2);
        customerList = customerRepository.findAll();
    }


    public Optional<Customer> getCustomer(String customerId) {
        return customerRepository.findById(customerId);
    }
}
