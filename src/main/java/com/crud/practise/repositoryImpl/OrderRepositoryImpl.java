package com.crud.practise.repositoryImpl;

import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.crud.practise.model.OrderDetails;
import com.crud.practise.repository.OrderdetailsRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
public class OrderRepositoryImpl implements OrderdetailsRepository {
	
	@Autowired
	private EntityManager entityManager; 

	@Override
	public Object[] getOrderDetailsById(int id) {
		Query query = entityManager.createNativeQuery("SELECT * FROM ORDERDETAILS WHERE ORDERID = ?");
		query.setParameter(1, id );
		Object[] orderDetail = null;
		try {
			orderDetail = (Object[]) query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return orderDetail;
	}

	@Override
	public List<Object[]> getAllOrderDetails() {
		Query query = entityManager.createNativeQuery("SELECT * FROM ORDERDETAILS");
		List<Object[]> orderList = null;
		try {
			orderList = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return orderList;
	}

	@Override
	@Transactional
	public OrderDetails saveOrderDetail(OrderDetails orderDetails) {
		Query query = entityManager.createNativeQuery("INSERT INTO ORDERDETAILS VALUES (?,?,?,?,?,?,?,?)");
		query.setParameter(1, orderDetails.getOrderId());
		query.setParameter(2, orderDetails.getOrderQty());
		query.setParameter(3, orderDetails.getPrice());
		query.setParameter(4, orderDetails.getProductId());
		query.setParameter(5, orderDetails.getCustomerId());
		query.setParameter(6, orderDetails.getEmployeeId());
		Date date = new Date();
		query.setParameter(7, date);
		query.setParameter(8, null);
		int recordInserted = 0;
		try {
			recordInserted = query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (recordInserted > 0) {
		return orderDetails;
		}
		else return null;
		
	}

	@Override
	@Transactional
	public OrderDetails updateOrderDetail(int orderId, OrderDetails orderDetails) {
		Query query = entityManager.createNativeQuery("UPDATE ORDERDETAILS SET QTY=?, PRICE_PER_UNIT=?, PRODUCTID=?, CUSTOMERID=?, EMPID=?, UPDATED_DATE=? WHERE ORDERID =?");
		query.setParameter(1, orderDetails.getOrderQty());
		query.setParameter(2, orderDetails.getPrice());
		query.setParameter(3, orderDetails.getProductId());
		query.setParameter(4, orderDetails.getCustomerId());
		query.setParameter(5, orderDetails.getEmployeeId());
		Date date = new Date();
		query.setParameter(6, date);
		query.setParameter(7, orderId);
		int recordUpdated = 0;
		try {
			recordUpdated =  query.executeUpdate();
		} catch (InputMismatchException e) {
			e.printStackTrace();
		}
		if (recordUpdated != 0) {
			return orderDetails;
		} else 
			return null;
	}

	@Override
	@Transactional
	public OrderDetails deleteOrderById(int orderId) throws Exception {
		Query query = entityManager.createNativeQuery("DELETE FROM ORDERDETAILS WHERE ORDERID = ?");
		query.setParameter(1, orderId);
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
			throw new Exception("Order not found the given ID");
	}
}
