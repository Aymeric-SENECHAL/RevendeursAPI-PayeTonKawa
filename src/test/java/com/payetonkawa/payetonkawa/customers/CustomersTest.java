package com.payetonkawa.payetonkawa.customers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomersTest {

    private Customers customers;

    @BeforeEach
    void setUp() {
        customers = new Customers();
    }

    @Test
    void testGetName() {
        customers.setName("Baptiste Zeni");

        assertEquals("Baptiste Zeni", customers.getName());
    }

    @Test
    void testGetUsername() {
        customers.setUsername("baptistezeni");
        assertEquals("baptistezeni", customers.getUsername());
    }



    @Test
    void testToString() {
        customers.setName("Baptiste Zeni");
        customers.setUsername("baptistezeni");
        customers.setFirstname("Baptiste");
        customers.setLastname("Zeni");
        customers.setPostalCode("000");
        customers.setAdressCity("Arras");
        customers.setProfileFirstname("baptiste");
        customers.setProfileLastname("zeni");
        customers.setCompanyName("epsi");
        customers.setEmail("baptiste@gmail.com");
        customers.setToken("0");
        String expectedToString = "Customers(name=Baptiste Zeni, username=baptistezeni, firstname=Baptiste, lastname=Zeni, postalCode=000, adressCity=Arras, profileFirstname=baptiste, profileLastname=zeni, companyName=epsi, email=baptiste@gmail.com, token=0, ordersList=null)";
        assertEquals(expectedToString, customers.toString());
    }


    @Test
    void testBuilder() {
        Customers builtCustomers = Customers.builder()
                .name("Baptiste Zeni")
                .username("baptistezeni")
                .build();

        assertEquals("Baptiste Zeni", builtCustomers.getName());
        assertEquals("baptistezeni", builtCustomers.getUsername());
    }
}
