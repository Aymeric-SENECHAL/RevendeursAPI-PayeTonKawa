package com.payetonkawa.payetonkawa.products;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IProductsService {
	List<Products> getAllProducts();

	Optional<Products> getProductsByID(UUID productID);

	Products createProducts(Products products);
}
