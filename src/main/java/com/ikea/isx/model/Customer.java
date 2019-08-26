package com.ikea.isx.model;


import lombok.Data;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
@Data
public class Customer {
    @Id
    private String customerid;
    private String customername;

    public Customer(String customerid, String customername) {
        this.customerid = customerid;
        this.customername = customername;
    }

    public Customer() {}
}
