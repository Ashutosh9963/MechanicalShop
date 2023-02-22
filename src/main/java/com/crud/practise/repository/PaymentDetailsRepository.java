package com.crud.practise.repository;

import java.util.List;

import com.crud.practise.model.PaymentDetails;

public interface PaymentDetailsRepository {
	
	public List<Object[]> getAllPaymentDetails();
	
	public Object getPaymentDetailById(int paymentId);
	
	public PaymentDetails createPaymentDetail(PaymentDetails paymentDetails);
	
	public Object updatePaymentDetailsById(int paymentId, PaymentDetails paymentDetails);
	
	public PaymentDetails deletePaymentDetailById(int paymentId) throws Exception;

}
