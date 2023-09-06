package com.payetonkawa.payetonkawa.orders;

import com.payetonkawa.payetonkawa.customers.Customers;
import com.payetonkawa.payetonkawa.products.Products;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class OrdersTest {
    private Orders orders;
    private Customers customers;
    private List<Products> productsList;
    @BeforeEach
    void setUp() {
        orders = new Orders();
        customers = mock(Customers.class);
        productsList = new ArrayList<>();
    }

    @Test
    void getCustomers() {
        orders.setCustomers(customers);
        assertEquals(customers, orders.getCustomers());
    }

    @Test
    void getProductsList() {
        orders.setProductsList(productsList);
        assertEquals(productsList, orders.getProductsList());
    }

    @Test
    void setCustomers() {
        orders.setCustomers(customers);
        assertEquals(customers, orders.getCustomers());
    }

    @Test
    void setProductsList() {
        orders.setProductsList(productsList);
        assertEquals(productsList, orders.getProductsList());
    }

    @Test
    void testToString() {
        UUID expectedUUID = UUID.fromString("550e8400-e29b-41d4-a716-446655440000");

        when(customers.getId()).thenReturn(expectedUUID);

        orders.setCustomers(customers);

        String expectedToString = "Orders\\(customers=Mock for Customers, hashCode: \\d+, productsList=null\\)";
        assertTrue(orders.toString().matches(expectedToString));
    }

    @Test
    void builder() {
        Orders builtOrder = Orders.builder()
                .customers(customers)
                .productsList(productsList)
                .build();

        assertEquals(customers, builtOrder.getCustomers());
        assertEquals(productsList, builtOrder.getProductsList());
    }
}