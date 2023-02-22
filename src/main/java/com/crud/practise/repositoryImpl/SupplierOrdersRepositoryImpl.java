package com.crud.practise.repositoryImpl;

import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.crud.practise.model.SupplierOrders;
import com.crud.practise.repository.SupplierOrdersRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
public class SupplierOrdersRepositoryImpl implements SupplierOrdersRepository {
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public List<Object[]> getAllSupplierOrdereDetails() {
		Query query = entityManager.createNativeQuery("SELECT * FROM SUPPLIERORDERS");
		List<Object[]> supplierOrderList = null;
		try {
			supplierOrderList = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return supplierOrderList;
	}

	@Override
	public Object getSupplierOrderDetailById(int supplierOrderId) {
		Query query = entityManager.createNativeQuery("SELECT * FROM  SUPPLIERORDERS WHERE SUPPLIERORDERID = ? ");
		query.setParameter(1, supplierOrderId );
		Object supplierOrderDetail = null;
		try {
			supplierOrderDetail =  query.getSingleResult();
		} catch (InputMismatchException e) {
			e.printStackTrace();
		}
		return supplierOrderDetail;
	}

	@Override
	@Transactional
	public SupplierOrders insertSupplierOrder(SupplierOrders supplierOrders) {
		Query query = entityManager.createNativeQuery("INSERT INTO SUPPLIERORDERS (ORDERED_QTY, SUPPLIERID, PRODUCTID, CREATED_DATE) VALUES (?,?,?,?)");
		query.setParameter(1, supplierOrders.getOrderedQty());
		query.setParameter(2, supplierOrders.getSupplierId());
		query.setParameter(3, supplierOrders.getProductId());
		Date date = new Date();
		query.setParameter(4, date);
		int recordInserted = 0;
		try {
			recordInserted = query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (recordInserted > 0) {
		return supplierOrders;
		}
		else return null;
	}

	@Override
	@Transactional
	public Object updateSupplierOrderById(int supplierOrderId, SupplierOrders supplierOrders) {
		Query query = entityManager.createNativeQuery("UPDATE SUPPLIERORDERS SET ORDERED_QTY=?, SUPPLIERID=?, PRODUCTID=?, UPDATED_DATE=? WHERE SUPPLIERORDERID = ? ");
		query.setParameter(1, supplierOrders.getOrderedQty());
		query.setParameter(2, supplierOrders.getSupplierId());
		query.setParameter(3, supplierOrders.getProductId());
		Date date = new Date();
		query.setParameter(4, date);
		query.setParameter(5, supplierOrderId);
		int recordUpdated = 0;
		try {
			recordUpdated =  query.executeUpdate();
		} catch (InputMismatchException e) {
			e.printStackTrace();
		}
		if (recordUpdated != 0) {
			return supplierOrders;
		} else 
			return null;
	}

	@Override
	@Transactional
	public SupplierOrders deleteSupplierOrderDetailById(int supplierOrderId) throws Exception {
		Query query = entityManager.createNativeQuery("DELETE FROM SUPPLIERORDERS WHERE SUPPLIERORDERID = ? ");
		query.setParameter(1, supplierOrderId);
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
			throw new Exception("SupplierOrderDetails not found the given ID");
	}

}
