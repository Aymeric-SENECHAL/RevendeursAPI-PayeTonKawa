package com.payetonkawa.payetonkawa.products;

import lombok.Builder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Builder
public class ProductsServiceImpl implements IProductsService {
	private final ProductsRepository productsRepository;

	@Override
	public List<Products> getAllProducts() {
		return productsRepository.findAll();
	}

}

