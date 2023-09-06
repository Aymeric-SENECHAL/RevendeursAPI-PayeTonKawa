package com.payetonkawa.payetonkawa.products;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductsTest {
    private Products product;

    @BeforeEach
    void setUp() {
        product = new Products();
    }

    @Test
    void testToString() {
        product.setPrice("10.99");
        product.setName("Cafetiere");
        product.setDescription("Cafetiere avec filtre");
        product.setColor("Rouge");
        product.setStock("100");
        String expectedToString = "Products(price=10.99, name=Cafetiere, description=Cafetiere avec filtre, color=Rouge, stock=100, ordersList=null)";
        assertEquals(expectedToString, product.toString());
    }
}