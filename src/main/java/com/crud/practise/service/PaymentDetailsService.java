package com.crud.practise.service;

import com.crud.practise.model.FinalResponse;
import com.crud.practise.model.PaymentDetails;

public interface PaymentDetailsService {
	
	public FinalResponse getAllPaymentDetails();
	
	public FinalResponse getPaymentDetailsById(int paymentId);
	
	public FinalResponse insertPaymentDetails(PaymentDetails paymentDetails);
	
	public FinalResponse updatePaymentDetailsById(int paymentId, PaymentDetails paymentDetails);
	
	public FinalResponse deletePaymentDetailsById(int paymentId) throws Exception;

}
