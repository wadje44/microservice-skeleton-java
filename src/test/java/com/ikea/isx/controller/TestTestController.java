package com.ikea.isx.controller;

import com.ikea.isx.AbstractTest;
import com.ikea.isx.common.utils.CommonConstants;
import com.ikea.isx.model.Customer;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.HttpStatus;

import static junit.framework.TestCase.assertTrue;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
public class TestTestController extends AbstractTest {

    @Autowired
    TestController testController;

    @Test
    public void basicTest() throws Exception{
        assertThat(testController).isNotNull();
        assertTrue(true);
    }

    @Test
    public void getCustomer() throws Exception{
        Customer customer = new Customer("one","Jacob");
        String url = CommonConstants.JUNITTESTURL + CommonConstants.SLASH + customer.getCustomerid();
        this.mockMvc.perform(get(url)).andExpect(status().isOk()).andReturn();
    }

    @Test
    public void getCustomerForNullCustomerId() throws Exception{
        assertEquals(HttpStatus.BAD_REQUEST, testController.getCustomer(null).getStatusCode());
    }

    @Test
    public void controllerTestForUnavailableCustomer() throws Exception{
        String url = CommonConstants.JUNITTESTURL + CommonConstants.SLASH + "notPresent";
        this.mockMvc.perform(get(url)).andExpect(status().isBadRequest()).andReturn();
    }
}


