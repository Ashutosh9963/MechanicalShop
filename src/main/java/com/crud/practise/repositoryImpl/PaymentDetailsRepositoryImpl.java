package com.crud.practise.repositoryImpl;

import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.crud.practise.model.PaymentDetails;
import com.crud.practise.repository.PaymentDetailsRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
public class PaymentDetailsRepositoryImpl implements PaymentDetailsRepository{
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public List<Object[]> getAllPaymentDetails() {
		Query query = entityManager.createNativeQuery("SELECT * FROM PAYMENTDETAILS");
		List<Object[]> paymentDetailsList = null;
		try {
			paymentDetailsList = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return paymentDetailsList;
	}

	@Override
	public Object getPaymentDetailById(int paymentId) {
		Query query = entityManager.createNativeQuery("SELECT * FROM  PAYMENTDETAILS WHERE PAYMENTID = ? ");
		query.setParameter(1, paymentId );
		Object paymentDetail = null;
		try {
			paymentDetail =  query.getSingleResult();
		} catch (InputMismatchException e) {
			e.printStackTrace();
		}
		return paymentDetail;
	}

	@Override
	@Transactional
	public PaymentDetails createPaymentDetail(PaymentDetails paymentDetails) {
		Query query = entityManager.createNativeQuery("INSERT INTO PAYMENTDETAILS (PAYMENTTYPE, SHIPMODE, STATUS, CREATED_DATE) VALUES (?,?,?,?)");
		query.setParameter(1, paymentDetails.getPaymentType());
		query.setParameter(2, paymentDetails.getShipMode());
		query.setParameter(3, paymentDetails.getStatus());
		Date date = new Date();
		query.setParameter(4, date);
		int recordInserted = 0;
		try {
			recordInserted = query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (recordInserted > 0) {
		return paymentDetails;
		}
		else return null;
	}

	@Override
	@Transactional
	public Object updatePaymentDetailsById(int paymentId, PaymentDetails paymentDetails) {
		Query query = entityManager.createNativeQuery("UPDATE PAYMENTDETAILS SET PAYMENTTYPE=?, SHIPMODE=?, STATUS=?, UPDATED_DATE=? WHERE PAYMENTID = ? ");
		query.setParameter(1, paymentDetails.getPaymentType());
		query.setParameter(2, paymentDetails.getShipMode());
		query.setParameter(3, paymentDetails.getStatus());
		Date date = new Date();
		query.setParameter(4, date);
		query.setParameter(5, paymentId);
		int recordUpdated = 0;
		try {
			recordUpdated =  query.executeUpdate();
		} catch (InputMismatchException e) {
			e.printStackTrace();
		}
		if (recordUpdated != 0) {
			return paymentDetails;
		} else 
			return null;
	}

	@Override
	@Transactional
	public PaymentDetails deletePaymentDetailById(int paymentId) throws Exception {
		Query query = entityManager.createNativeQuery("DELETE FROM PAYMENTDETAILS WHERE PAYMENTID = ? ");
		query.setParameter(1, paymentId);
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
			throw new Exception("PaymentDetails not found the given ID");
	}
}
