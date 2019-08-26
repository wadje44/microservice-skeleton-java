package com.ikea.isx.service.impl;

import com.ikea.isx.common.utils.ErrorMessage;
import com.ikea.isx.dao.interfaces.CustomerDAO;
import com.ikea.isx.model.Customer;
import com.ikea.isx.service.interfaces.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerDAO customerDAO;

    public Customer getCustomer(String customerId) {
        Optional<Customer> optionalCust = customerDAO.getCustomer(customerId);
        if(optionalCust.isPresent()) {
            return optionalCust.get();
        } else {
            ErrorMessage err = new ErrorMessage("No Customer Present for " + customerId);
            log.info("CustomerServiceImpl getCustomer() :: " + err.toString());
            return null;
        }
    }

}
