package com.crud.practise.serviceImpl;

import java.util.InputMismatchException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.practise.model.FinalResponse;
import com.crud.practise.model.PaymentDetails;
import com.crud.practise.repository.PaymentDetailsRepository;
import com.crud.practise.service.PaymentDetailsService;

@Service
public class PaymentDetailsServiceImpl implements PaymentDetailsService{
	
	@Autowired
	private PaymentDetailsRepository repository;
	
	// Creating the Logger Object
	Logger logger = LoggerFactory.getLogger(SupplierOrdersServiceImpl.class);

	@Override
	public FinalResponse getAllPaymentDetails() {
		logger.info("Fetching all the PaymentDetails");
		FinalResponse finalResponse = new FinalResponse();
		Object paymentDetailsList = null; 
		try {
			paymentDetailsList = repository.getAllPaymentDetails();
		} catch (Exception e) {
			logger.error("Cannot fetch the PaymentDetails List" + e.getMessage());
		}
		if (paymentDetailsList != null) {
			finalResponse.setStatus(true);
			finalResponse.setStatusCode("200");
			finalResponse.setMessage("Records Inserted Successfully");
			finalResponse.setData(paymentDetailsList);
			return finalResponse;
		}
		else {
			finalResponse.setStatus(false);
			finalResponse.setStatusCode("501");
			finalResponse.setErrorMessage("Record cannot Inserted");
			return finalResponse;
		}
	}

	@Override
	public FinalResponse getPaymentDetailsById(int paymentId) {
		logger.info("Fetching PaymentDetails by Id: " + paymentId);
		FinalResponse finalResponse = new FinalResponse();
		Object paymentDetail = null; 
		try {
			paymentDetail = repository.getPaymentDetailById(paymentId);
		} catch (InputMismatchException e) {
			logger.error("Cannot fetcht the PaymentDetails by Id " + paymentId + e.getMessage());
		}
		if(paymentDetail!=null) {
			finalResponse.setStatus(true);
			finalResponse.setStatusCode("200");
			finalResponse.setMessage("Records Available");
			finalResponse.setData(paymentDetail);
			return finalResponse;
		}else {
			finalResponse.setStatus(false);
			finalResponse.setStatusCode("501");
			finalResponse.setMessage("Records not Available");
			return finalResponse;
		}
	}

	@Override
	public FinalResponse insertPaymentDetails(PaymentDetails paymentDetails) {
		FinalResponse finalResponse = new FinalResponse();
		logger.info("Inserting one Recored::input::paymentDetail: " + paymentDetails);
		Object paymentDetail =   repository.createPaymentDetail(paymentDetails);
		if(paymentDetail!=null) {
			finalResponse.setStatus(true);
			finalResponse.setStatusCode("201");
			finalResponse.setMessage("Record is inserted");
			finalResponse.setData(paymentDetail);
			return finalResponse;
		}else {
			finalResponse.setStatus(false);
			finalResponse.setStatusCode("501");
			finalResponse.setErrorMessage("Record is not inserted");
			return finalResponse;
		}
	}

	@Override
	public FinalResponse updatePaymentDetailsById(int paymentId, PaymentDetails paymentDetails) {
		logger.info("Updating paymentDetail by Id: " + paymentId);
		FinalResponse finalResponse = new FinalResponse();
		Object updatedDetail = null;
		try {
			updatedDetail = repository.updatePaymentDetailsById(paymentId, paymentDetails);
		} catch (InputMismatchException e) {
			logger.error("Cannot Updated the paymentDetail for Id:" + paymentId);
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
	public FinalResponse deletePaymentDetailsById(int paymentId) throws Exception {
		logger.info("Deleting PaymentDetail by Id: " + paymentId);
		FinalResponse finalResponse = new FinalResponse();
		try {
			repository.deletePaymentDetailById(paymentId);
		} catch (InputMismatchException e) {
			e.printStackTrace();
			logger.error("Cannot deleted the PaymentDetail by ID" + paymentId + e.getMessage());
		}
		if(paymentId !=0) {
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
