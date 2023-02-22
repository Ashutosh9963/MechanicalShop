package com.crud.practise.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.crud.practise.model.FinalResponse;
import com.crud.practise.model.Products;
import com.crud.practise.serviceImpl.ProductsServiceImpl;

import jakarta.validation.Valid;

@RestController
public class ProductsController {
	
	@Autowired
	private ProductsServiceImpl service;
	
	// Creating a logger object 
		Logger logger =   LoggerFactory.getLogger(ProductsController.class);
		
		@GetMapping("/getAllProductsDetails")
		public FinalResponse getAllCategoryDetails() {
			logger.info("To get all Products Details");
			return service.getAllProductsDetails();
		}
		
		@GetMapping("/getProductDetailsById/{id}")
		public FinalResponse getCategoryDetailById(@PathVariable int id) {
			logger.info("To get ProductDetails by  Id: " , id);
			return service.getProductDetailsById(id);
		}
		
		@PutMapping("/updateProductDetailById/{id}")
		public FinalResponse updateProductDetail(@PathVariable int id, @RequestBody @Valid Products products) {
			logger.info("Updating the Category, Id", id);
			return service.updateDetailsById(id, products);
		}
		
		@PostMapping("/saveProdctDetail")
		public FinalResponse saveProducDetails(@RequestBody @Valid Products products) {
			logger.info("Creating a category");
			return service.saveProductDetails(products);
		}
		
		@DeleteMapping("/deleteProductById/{id}")
		public FinalResponse deleteProductById(@PathVariable  int id ) throws Throwable {
			logger.info("Deleting the Student, Id: " + id);
			return service.deleteDetailsById(id);
		}
}
