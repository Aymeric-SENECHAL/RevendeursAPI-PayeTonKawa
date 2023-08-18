package com.payetonkawa.payetonkawa.products;


import com.payetonkawa.payetonkawa.customers.CustomersRepository;
import lombok.Builder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin()
@RestController
@Builder
@RequestMapping("/api/v1/")
public class ProductsController {
	private IProductsService productsService;
	private CustomersRepository customersRepository;

	@GetMapping("/products")
	public List<Products> getAllProducts(@RequestParam String token){
		if(customersRepository.findCustomersByToken(token).isPresent()){
			return productsService.getAllProducts();
		}else{
			return new ArrayList<>();
		}
	}

	@GetMapping("/products/{productID}")
	public Optional<Products> getProductsByID(@RequestParam String token, @PathVariable UUID productID){
		if(customersRepository.findCustomersByToken(token).isPresent()){
			return productsService.getProductsByID(productID);
		}else{
			return null;
		}
	}

	@PostMapping("/products")
	public Products createProducts(@RequestParam String token, @RequestBody Products products) {
		if(customersRepository.findCustomersByToken(token).isPresent()){
			return productsService.createProducts(products);
		}else{
			return null;
		}
	}
}
