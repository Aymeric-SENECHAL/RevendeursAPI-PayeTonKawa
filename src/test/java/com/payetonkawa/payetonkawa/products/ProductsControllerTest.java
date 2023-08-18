package com.payetonkawa.payetonkawa.products;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.payetonkawa.payetonkawa.customers.Customers;
import com.payetonkawa.payetonkawa.customers.CustomersRepository;
import com.payetonkawa.payetonkawa.products.IProductsService;
import com.payetonkawa.payetonkawa.products.Products;
import com.payetonkawa.payetonkawa.products.ProductsController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(ProductsController.class)
public class ProductsControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IProductsService productsService;

    @MockBean
    private CustomersRepository customersRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllProducts() throws Exception {
        // Mock the customersRepository behavior
        when(customersRepository.findCustomersByToken("valid_token")).thenReturn(Optional.of(new Customers()));

        // Mock the productsService behavior
        List<Products> productList = List.of(
                Products.builder().name("Product 1").build(),
                Products.builder().name("Product 2").build()
        );
        when(productsService.getAllProducts()).thenReturn(productList);

        // Perform a GET request to the endpoint /api/v1/products with valid token
        mockMvc.perform(get("/api/v1/products").param("token", "valid_token"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("Product 1"))
                .andExpect(jsonPath("$[1].name").value("Product 2"));

        // Perform a GET request to the endpoint /api/v1/products with invalid token
        mockMvc.perform(get("/api/v1/products").param("token", "invalid_token"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));
    }
    @Test
    public void testGetProductsByID() throws Exception {
        UUID productID = UUID.randomUUID();
        Products product = Products.builder()
                .name("Test Product")
                .build();
        product.setId(productID);
        // Mock the customersRepository behavior
        when(customersRepository.findCustomersByToken("valid_token")).thenReturn(Optional.of(new Customers()));

        // Mock the productsService behavior
        when(productsService.getProductsByID(productID)).thenReturn(Optional.of(product));

        // Perform a GET request to the endpoint /api/v1/products/{productID} with valid token
        mockMvc.perform(get("/api/v1/products/{productID}", productID)
                        .param("token", "valid_token"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(productID.toString()))
                .andExpect(jsonPath("$.name").value("Test Product"));
    }

    @Test
    public void testGetProductsByID_InvalidToken() throws Exception {
        UUID productID = UUID.randomUUID();

        // Mock the customersRepository behavior
        when(customersRepository.findCustomersByToken("invalid_token")).thenReturn(Optional.empty());

        // Perform a GET request to the endpoint /api/v1/products/{productID} with invalid token
        mockMvc.perform(get("/api/v1/products/{productID}", productID)
                        .param("token", "invalid_token"))
                .andExpect(status().isOk())
                .andExpect(content().string("")); // Endpoint returns null when the token is invalid
    }

    @Test
    public void testGetProductsByID_ProductNotFound() throws Exception {
        UUID productID = UUID.randomUUID();

        // Mock the customersRepository behavior
        when(customersRepository.findCustomersByToken("valid_token")).thenReturn(Optional.of(new Customers()));

        // Mock the productsService behavior (product not found)
        when(productsService.getProductsByID(productID)).thenReturn(Optional.empty());

        // Perform a GET request to the endpoint /api/v1/products/{productID} with valid token
        mockMvc.perform(get("/api/v1/products/{productID}", productID)
                        .param("token", "valid_token"))
                .andExpect(status().isOk())
                .andExpect(content().string("null")); // Endpoint returns null when the product is not found
    }
    @Test
    public void testCreateProducts() throws Exception {
        String token = "valid_token";
        Products product = Products.builder()
                .name("Test Product")
                .build();
        product.setId(UUID.randomUUID());

        // Mock the customersRepository behavior
        when(customersRepository.findCustomersByToken(token)).thenReturn(Optional.of(new Customers()));

        // Mock the productsService behavior
        when(productsService.createProducts(product)).thenReturn(product);

        // Perform a POST request to the endpoint /api/v1/products with valid token
        mockMvc.perform(post("/api/v1/products")
                        .param("token", token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(product)))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateProducts_InvalidToken() throws Exception {
        String token = "invalid_token";
        Products product = Products.builder()
                .name("Test Product")
                .build();

        // Mock the customersRepository behavior
        when(customersRepository.findCustomersByToken(token)).thenReturn(Optional.empty());

        // Perform a POST request to the endpoint /api/v1/products with invalid token
        mockMvc.perform(post("/api/v1/products")
                        .param("token", token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(product)))
                .andExpect(status().isOk())
                .andExpect(content().string("")); // Endpoint returns null when the token is invalid
    }

    // Helper method to convert an object to a JSON string
    private String asJsonString(Object obj) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
