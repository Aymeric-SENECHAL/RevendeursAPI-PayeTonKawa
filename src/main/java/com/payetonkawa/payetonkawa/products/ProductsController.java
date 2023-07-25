package com.payetonkawa.payetonkawa.products;


import lombok.Builder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin()
@RestController
@Builder
@RequestMapping("/api/v1/")
public class ProductsController {
	private IProductsService productsService;

	@GetMapping("/products")
	public List<Products> getAllProducts(){
		return productsService.getAllProducts();
	}

	@GetMapping("/products/{productID}")
	public Optional<Products> getProductsByID(@PathVariable UUID productID){
		return productsService.getProductsByID(productID);
	}

	@PostMapping("/products")
	public Products createProducts(@RequestBody Products products) {
		return productsService.createProducts(products);
	}

}
