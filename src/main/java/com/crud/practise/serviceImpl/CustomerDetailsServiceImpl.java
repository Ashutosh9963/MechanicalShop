 package com.crud.practise.serviceImpl;

import java.util.InputMismatchException; 

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.practise.model.CustomerDetails;
import com.crud.practise.model.FinalResponse;
import com.crud.practise.repository.CustomerRepository;
import com.crud.practise.service.CustomerDetailsService;

@Service
public class CustomerDetailsServiceImpl implements CustomerDetailsService{
	
	@Autowired
	 private CustomerRepository customerRepository;
	
	Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

	@Override
	public FinalResponse getAllCustomerDetails() {
		logger.info("Fetching all the Customer Details");
		FinalResponse finalResponse = new FinalResponse();
		Object customerDetails = null;
		try {
			customerDetails = customerRepository.getAllCustomerDetails();
		} catch (Exception e) {
			logger.error("Cannot fetch the Customer Details List" + e.getMessage());
		}
		if(customerDetails!=null) {
			finalResponse.setStatus(true);
			finalResponse.setStatusCode("200");
			finalResponse.setMessage("Records is  present");
			finalResponse.setData(customerDetails );
			return finalResponse;
		}else {
			finalResponse.setStatus(false);
			finalResponse.setStatusCode("501");
			finalResponse.setErrorMessage("Record not available to display");
			return finalResponse;
		}
	}

	@Override
	public FinalResponse getAllCustomerDetailsById(int customerId) {
		logger.info("Fetching Customer Details by Id: " + customerId);
		FinalResponse finalResponse = new FinalResponse();
		Object customerDetails = null; 
		try {
			customerDetails = customerRepository.getAllCustomerDetailsById(customerId);
		} catch (InputMismatchException e) {
			logger.error("Cannot fetcht the Category Details by Id " + customerId + e.getMessage());
		}
		if(customerDetails!=null) {
			finalResponse.setStatus(true);
			finalResponse.setStatusCode("200");
			finalResponse.setMessage("Records Available");
			finalResponse.setData(customerDetails);
			return finalResponse;
		}else {
			finalResponse.setStatus(false);
			finalResponse.setStatusCode("501");
			finalResponse.setMessage("Records not Available");
			return finalResponse;
		}
	}

	@Override
	public FinalResponse insertCustomerDetail(CustomerDetails customerDetails) {
		logger.info("Inserting one Recored::input::customerDetails: " + customerDetails );
		Object newRecored = customerRepository.insertCustomerDetail(customerDetails);
		FinalResponse finalResponse = new FinalResponse();
		if(newRecored!=null) {
			finalResponse.setStatus(true);
			finalResponse.setStatusCode("201");
			finalResponse.setMessage("Record is inserted");
			finalResponse.setData(newRecored);
			return finalResponse;
		}else {
			finalResponse.setStatus(false);
			finalResponse.setStatusCode("501");
			finalResponse.setErrorMessage("Record is not inserted");
			return finalResponse;
		}
	}

	@Override
	public FinalResponse updateDetail(int customerid, CustomerDetails customerDetails) {
		logger.info("Updating Product Details by Id: " + customerid);
		customerRepository.updateDetail(customerid, customerDetails);
		FinalResponse finalResponse = new FinalResponse();
		Object updatedDetail = null;
		try {
			updatedDetail = customerRepository.updateDetail(customerid, customerDetails);
		} catch (InputMismatchException e) {
			logger.error("Cannot Updated the CustomerDetails for Id:" + customerid);
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
	public FinalResponse deleteRecordById(int customerid) {
		logger.info("Deleting Customer Details by Id: " + customerid);
		int deletedRecord = 0;
		FinalResponse finalResponse = new FinalResponse();
		try {
			deletedRecord = customerRepository.deleteRecordById(customerid);
		} catch (InputMismatchException e) {
			e.printStackTrace();
			logger.error("Cannot deleted the CustomerDetail by the ID " + customerid + e.getMessage());
		}
		if(deletedRecord == 1) {
			finalResponse.setStatus(true);
			finalResponse.setStatusCode("204");
			finalResponse.setMessage("Record is deleted for the given ID" + customerid);
			return finalResponse;
		}else {
			finalResponse.setStatus(false);
			finalResponse.setStatusCode("501");
			finalResponse.setMessage("Record not available to deleted for the given ID");
			return finalResponse;
		}
	}
}
