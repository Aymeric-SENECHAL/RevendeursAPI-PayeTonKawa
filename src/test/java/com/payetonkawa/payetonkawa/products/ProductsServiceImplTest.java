package com.payetonkawa.payetonkawa.products;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ProductsServiceImplTest {
    @Mock
    private ProductsRepository productsRepository;

    @InjectMocks
    private ProductsServiceImpl productsService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllProducts() {
        // Créer des données de test
        List<Products> productList = new ArrayList<>();
        productList.add(Products.builder()
                .name("Product 1")
                .price("10.99")
                .description("Description du produit 1")
                .color("Blue")
                .stock("In Stock")
                .build());

        productList.add(Products.builder()
                .name("Product 2")
                .price("19.99")
                .description("Description du produit 2")
                .color("Red")
                .stock("Out of Stock")
                .build());

        when(productsRepository.findAll()).thenReturn(productList);

        // Appeler la méthode du service
        List<Products> result = productsService.getAllProducts();

        // Vérifier que la méthode du repository a été appelée
        verify(productsRepository, times(1)).findAll();

        // Vérifier que les résultats sont corrects
        assertEquals(2, result.size());
        assertEquals("Product 1", result.get(0).getName());
        assertEquals("Description du produit 1", result.get(0).getDescription());
        assertEquals("Product 2", result.get(1).getName());
        assertEquals("Description du produit 2", result.get(1).getDescription());
    }

    @Test
    public void testGetProductsByID() {
        // Créer une ID de produit aléatoire
        UUID productID = UUID.randomUUID();

        // Créer un objet de produit simulé pour le résultat attendu
        Products product = Products.builder()
                .name("Test Product")
                .price("25.99")
                .description("Description du produit de test")
                .color("Green")
                .stock("In Stock")
                .build();

        when(productsRepository.findById(productID)).thenReturn(Optional.of(product));

        // Appeler la méthode du service
        Optional<Products> result = productsService.getProductsByID(productID);

        // Vérifier que la méthode du repository a été appelée
        verify(productsRepository, times(1)).findById(productID);

        // Vérifier que le résultat est correct
        assertEquals(product, result.orElse(null));
    }

    @Test
    public void testCreateProducts() {
        // Créer un produit simulé
        Products product = Products.builder()
                .name("New Product")
                .price("12.50")
                .description("Description du nouveau produit")
                .color("Yellow")
                .stock("In Stock")
                .build();

        // Définir le comportement du repository lors de l'appel à save()
        when(productsRepository.save(product)).thenReturn(product);

        // Appeler la méthode du service
        Products result = productsService.createProducts(product);

        // Vérifier que la méthode du repository a été appelée
        verify(productsRepository, times(1)).save(product);

        // Vérifier que le résultat est correct
        assertEquals(product, result);
    }
}
