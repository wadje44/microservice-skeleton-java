package com.ikea.isx.controller;
import com.ikea.isx.common.utils.ErrorMessage;
import com.ikea.isx.service.interfaces.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ikea.isx.model.Customer;


@RestController
@RequestMapping("/catalog")
@Slf4j
public class TestController {
    @Autowired
    CustomerService customerService;

    @GetMapping("/{customerId}")
    public ResponseEntity<Object> getCustomer(@PathVariable("customerId") String customerId) {
        log.info("TestController getCustomer() :: GET api catalog/{customerId} customerId " + customerId);
        if( customerId == null) {
            ErrorMessage err = new ErrorMessage("customerId can not be null");
            log.error("TestController getCustomer() :: " + err.toString());
            return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
        } else {
            Customer cust = customerService.getCustomer(customerId);
            if((cust == null) || (cust.getCustomerid() == null) || !(cust.getCustomerid().equals(customerId))) {
                ErrorMessage err = new ErrorMessage("No Records found for the Customer "+ customerId);
                log.info("TestController getCustomer() :: " + err);
                ResponseEntity<Object> responseEntity = new ResponseEntity<>(err.toString(), HttpStatus.BAD_REQUEST);
                log.info("TestController getCustomer() :: getclass " +  responseEntity.getBody().getClass());

                log.info("TestController getCustomer() responseEntity:: " + responseEntity);

                return responseEntity;
            } else {
                log.info("Customer found for customerId " +  customerId);
                log.info("TestController getCustomer() responseEntity:: " + new ResponseEntity<>(cust , HttpStatus.OK));

                return new ResponseEntity<>(cust , HttpStatus.OK);
            }
        }

    }
}
