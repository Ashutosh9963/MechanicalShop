package com.crud.practise.repositoryImpl;

import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.crud.practise.model.CategoryDetails;
import com.crud.practise.model.Products;
import com.crud.practise.repository.ProductsRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
public class ProductsRepositoryImpl implements ProductsRepository{
	
	@Autowired
	EntityManager entityManager;
	
	@Override
	public List<Object[]> getAllProductsDetails() {
		Query query = entityManager.createNativeQuery("SELECT PRODUCTID, PRODUCTNAME, QTY, ITEM_PRICE, CATEGORYID, WAREHOUSEID, CREATED_DATE, UPDATED_DATE FROM PRODUCTS");
		List<Object[]> productList = null;
		try {
			productList = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return productList;
	}

	@Override
	public Object[] getProductDetailById(int productId) {
		Query query = entityManager.createNativeQuery("SELECT PRODUCTID, PRODUCTNAME, QTY, ITEM_PRICE, CATEGORYID, WAREHOUSEID, CREATED_DATE, UPDATED_DATE FROM PRODUCTS WHERE PRODUCTID = ? ");
		query.setParameter(1, productId );
		Object[] productDetail = null;
		try {
			productDetail = (Object[]) query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return productDetail;
	}

	
	@Override
	@Transactional
	public Products saveProduct(Products products) {
		Query query = entityManager.createNativeQuery("INSERT INTO PRODUCTS ( PRODUCTNAME, QTY, ITEM_PRICE, CATEGORYID, WAREHOUSEID, CREATED_DATE)  VALUES (?,?,?,?,?,?)");
		query.setParameter(1, products.getProductName());
		query.setParameter(2, products.getQty());
		query.setParameter(3, products.getItemPrice());
		query.setParameter(4, products.getCategoryId());
		query.setParameter(5, products.getWarehouseId());
		Date date = new Date();
		query.setParameter(6, date);
		int recordInserted = 0;
		try {
			recordInserted = query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (recordInserted > 0) {
		return products;
		}
		else return null;
	}

	@Override
	@Transactional
	public Products updateProductDetails(int productId, Products products) {
		Query query = entityManager.createNativeQuery("UPDATE PRODUCTS SET PRODUCTNAME=?, QTY=?, ITEM_PRICE=?, CATEGORYID=?, WAREHOUSEID=?, UPDATED_DATE=? WHERE PRODUCTID = ? ");
		query.setParameter(1, products.getProductName());
		query.setParameter(2, products.getQty());
		query.setParameter(3, products.getItemPrice());
		query.setParameter(4, products.getCategoryId());
		query.setParameter(5, products.getWarehouseId());
		Date date = new Date();
		query.setParameter(6, date);
		query.setParameter(7, productId);
		int recordUpdated = 0;
		try {
			recordUpdated =  query.executeUpdate();
		} catch (InputMismatchException e) {
			e.printStackTrace();
		}
		if (recordUpdated != 0) {
			return products;
		} else 
			return null;
	}

	@Override
	@Transactional
	public Products deleteById(int productId) throws Exception {
		
		Query query = entityManager.createNativeQuery("DELETE FROM PRODUCTS WHERE PRODUCTID = ? ");
		query.setParameter(1, productId);
		int deleteRecord = 0; 
		try {
			deleteRecord = query.executeUpdate();
		} catch (InputMismatchException e) {
			e.printStackTrace();
		}
		if (deleteRecord > 0) {
			return null;
		}
		else
			throw new Exception("Product not found the given ID");
	}

}
