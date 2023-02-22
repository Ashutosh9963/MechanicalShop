package com.crud.practise.serviceImpl;

import java.util.InputMismatchException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.practise.model.FinalResponse;
import com.crud.practise.model.Products;
import com.crud.practise.repository.ProductsRepository;
import com.crud.practise.service.ProductService;

@Service
public class ProductsServiceImpl implements ProductService{
	
	@Autowired
	private ProductsRepository repository;

	Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

	@Override
	public FinalResponse getAllProductsDetails() {
		logger.info("Fetching all the Products Details");
		Object productList = null; 
		try {
			productList = repository.getAllProductsDetails();
		} catch (Exception e) {
			logger.error("Cannot fetch the  ProductsDetails List" + e.getMessage());
		}
		FinalResponse finalResponse = new FinalResponse();
		if (productList != null) {
			finalResponse.setStatus(true);
			finalResponse.setStatusCode("200");
			finalResponse.setMessage("Records present");
			finalResponse.setData(productList);
			return finalResponse;
		}
		else {
			finalResponse.setStatus(false);
			finalResponse.setStatusCode("501");
			finalResponse.setErrorMessage("Record not available to display");
			return finalResponse;
		}
	}

	@Override
	public FinalResponse getProductDetailsById(int productId) {
		logger.info("Fetching Products Details by Id: " + productId);
		FinalResponse finalResponse = new FinalResponse();
		Object productDetails = null; 
		try {
			productDetails = repository.getProductDetailById(productId);
		} catch (InputMismatchException e) {
			logger.error("Cannot fetcht the productsDetails by Id " + productId + e.getMessage());
		}
		if(productDetails!=null) {
			finalResponse.setStatus(true);
			finalResponse.setStatusCode("200");
			finalResponse.setMessage("Records Available");
			finalResponse.setData(productDetails);
			return finalResponse;
		}else {
			finalResponse.setStatus(false);
			finalResponse.setStatusCode("501");
			finalResponse.setErrorMessage("Records not Available");
			return finalResponse;
		}
	}

	@Override
	public FinalResponse saveProductDetails(Products products) {
		logger.info("Inserting one Recored::input::productDetails: " + products );
		FinalResponse finalResponse = new FinalResponse();
		Object newRecord = repository.saveProduct(products);
		if(newRecord!=null) {
			finalResponse.setStatus(true);
			finalResponse.setStatusCode("201");
			finalResponse.setMessage("Record is inserted");
			finalResponse.setData(newRecord);
			return finalResponse;
		}else {
			finalResponse.setStatus(false);
			finalResponse.setStatusCode("501");
			finalResponse.setErrorMessage("Record is not inserted");
			return finalResponse;
		}
	}

	@Override
	public FinalResponse updateDetailsById(int productId, Products products) {
		logger.info("Updating Product Details by Id: " + productId);
		FinalResponse finalResponse = new FinalResponse();
		Products updatedDetail = null;
		try {
			updatedDetail =  repository.updateProductDetails(productId, products);
		} catch (InputMismatchException e) {
			logger.error("Cannot Updated the productDetails for Id:" + productId);
		}
		if(updatedDetail!=null) {
			finalResponse.setStatus(true);
			finalResponse.setStatusCode("200");
			finalResponse.setMessage("Record Updated Successfully");
			finalResponse.setData(updatedDetail);
			return finalResponse;
		}else {
			finalResponse.setStatus(false);
			finalResponse.setStatusCode("501");
			finalResponse.setErrorMessage("Record is not Updated");
			return finalResponse;
		}
	}

	@Override
	public FinalResponse deleteDetailsById(int productId) throws Exception {
		logger.info("Deleting Product Details by Id: " + productId);
		FinalResponse finalResponse = new FinalResponse();
		try {
			repository.deleteById(productId);
		} catch (InputMismatchException e) {
			logger.error("Cannot deleted the productDetail by ID" + productId + e.getMessage());
		}
		if(productId !=0) {
			finalResponse.setStatus(true);
			finalResponse.setStatusCode("204");
			finalResponse.setMessage("Record is deleted");
			return finalResponse;
		}else {
			finalResponse.setStatus(false);
			finalResponse.setStatusCode("501");
			finalResponse.setErrorMessage("Record is not deleted");
			return finalResponse;
		}
	}
}
