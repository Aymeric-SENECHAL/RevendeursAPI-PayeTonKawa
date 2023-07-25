package com.payetonkawa.payetonkawa.products;

import lombok.Builder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Builder
public class ProductsServiceImpl implements IProductsService {
	private final ProductsRepository productsRepository;

	@Override
	public List<Products> getAllProducts() {
		return productsRepository.findAll();
	}

	@Override
	public Optional<Products> getProductsByID(UUID productID) {
		return productsRepository.findById(productID);
	}

	@Override
	public Products createProducts(Products products){ return productsRepository.save(products);}

}

