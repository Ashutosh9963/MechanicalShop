package com.crud.practise.repositoryImpl;

import java.util.Date;
import java.util.InputMismatchException;
import java.util.List; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.crud.practise.model.CustomerDetails;
import com.crud.practise.repository.CustomerRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository{
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public List<Object[]> getAllCustomerDetails() {
		Query query = entityManager.createNativeQuery("SELECT * FROM CUSTOMERDETAILS");
		List<Object[]> customerList = null;
		try {
			customerList = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return customerList;
	}

	@Override
	@Transactional
	public Object insertCustomerDetail(CustomerDetails customerDetails) {
		Query query = entityManager.createNativeQuery("INSERT INTO CUSTOMERDETAILS (CUSTOMERNAME, MOBILE, ADDRESS, CREATED_DATE) VALUES (?,?,?,?)");
		query.setParameter(1, customerDetails.getCustomername());
		query.setParameter(2, customerDetails.getMobile());
		query.setParameter(3, customerDetails.getAddress());
		Date date = new Date();
		query.setParameter(4, date);
		int recordInserted = 0;
		try {
			recordInserted = query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (recordInserted > 0) {
		return customerDetails;
		}
		else return null;
 	}
	
	@Transactional
	@Override
	public CustomerDetails updateDetail(int customerid, CustomerDetails customerDetails) {
		Query query = entityManager.createNativeQuery("update customerdetails set customername=?, mobile = ?, address= ?, updated_date=? where customerid = ?");
		query.setParameter(1, customerDetails.getCustomername());
		query.setParameter(2, customerDetails.getMobile());
		query.setParameter(3, customerDetails.getAddress());
		Date date = new Date();
		query.setParameter(4, date);
		query.setParameter(5, customerid);
		int recordUpdated = 0;
		try {
			recordUpdated =  query.executeUpdate();
		} catch (InputMismatchException e) {
			e.printStackTrace();
		}
		if (recordUpdated != 0) {
			return customerDetails;
		} else {
			return null;
		}
	}

	@Transactional
	@Override
	public int  deleteRecordById(int customerid) {
		Query query = entityManager.createNativeQuery("DELETE FROM CUSTOMERDETAILS WHERE CUSTOMERID = ? ");
		query.setParameter(1, customerid);
		int recordDeleted = 0;
		try {
			recordDeleted = query.executeUpdate();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		}
		if (recordDeleted == 1) {
			recordDeleted =1;
		}
		return recordDeleted;
	}
	
	@Override
	public Object[] getAllCustomerDetailsById(int customerid) {
		Query query = entityManager.createNativeQuery("SELECT * FROM CUSTOMERDETAILS WHERE CUSTOMERID = ? ");
		query.setParameter(1, customerid);
		Object[] customerDetail = null;
		try {
			customerDetail =  (Object[]) query.getSingleResult();
		} catch (InputMismatchException e) {
			e.printStackTrace();
		}
		return customerDetail;
	}

}
