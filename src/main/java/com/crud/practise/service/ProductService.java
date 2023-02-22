package com.crud.practise.service;

import com.crud.practise.model.FinalResponse;
import com.crud.practise.model.Products;

public interface ProductService {
	
	public FinalResponse getAllProductsDetails();
	
	public FinalResponse getProductDetailsById(int productId);
	
	public FinalResponse saveProductDetails(Products products);
	
	public FinalResponse updateDetailsById(int productId, Products products);
	
	public FinalResponse deleteDetailsById(int productId) throws Exception;

}
