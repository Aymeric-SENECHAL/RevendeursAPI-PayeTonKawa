package com.payetonkawa.payetonkawa.products;


import lombok.Builder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin()
@RestController
@Builder
@RequestMapping("/api/v1/")
public class ProductsController {
	private IProductsService productsService;

	@GetMapping("/products")
	public List<Products> getAllTags(){
		return productsService.getAllProducts();
	}

}
