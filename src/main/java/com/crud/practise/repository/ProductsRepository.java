package com.crud.practise.repository;

import java.util.List;

import com.crud.practise.model.Products;

public interface ProductsRepository {
	
	public List<Object[]> getAllProductsDetails();
	
	public Object[] getProductDetailById(int productId);
	
	public Products saveProduct(Products products);
	
	public Products updateProductDetails(int productId , Products products);
	
	public Products deleteById(int productId) throws Exception;

}
