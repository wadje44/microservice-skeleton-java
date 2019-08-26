package com.ikea.isx.dao.interfaces;

import com.ikea.isx.model.Customer;

import java.util.Optional;

public interface CustomerDAO {
    public Optional<Customer> getCustomer(String customerId);
}
